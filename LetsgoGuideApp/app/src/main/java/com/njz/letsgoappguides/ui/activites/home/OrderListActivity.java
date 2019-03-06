package com.njz.letsgoappguides.ui.activites.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.EndlessRecyclerOnScrollListener;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.home.OrderListAdapter;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyClickLisener;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView;
import com.njz.letsgoappguides.model.home.OrderListModel;
import com.njz.letsgoappguides.presenter.home.OrderConfirmContract;
import com.njz.letsgoappguides.presenter.home.OrderConfirmPresenter;
import com.njz.letsgoappguides.presenter.home.OrderListContract;
import com.njz.letsgoappguides.presenter.home.OrderListPresenter;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshOrderListEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderListActivity extends BaseActivity implements OrderListContract.View,OrderConfirmContract.View{


    @BindView(R.id.titleView)
    public TitleView titleView;
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
//    @BindView(R.id.swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_parent)
    public LinearLayout layoutParent;
//    @BindView(R.id.view_empty)
    public EmptyView emptyView;

    public LoadMoreWrapper loadMoreWrapper;
    public  OrderListAdapter mAdapter;

    OrderListPresenter mPresenter;
    OrderConfirmPresenter confirmPresenter;

    int payStatus;
    int orderStatus;

    public int page;
    public int isLoadType = 1;//1下拉刷新，2上拉加载
    public boolean isLoad = false;//是否在加载，重复加载问题
    Disposable refreshDisposable;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout=$(R.id.swipe_refresh_layout);
        emptyView=$(R.id.view_empty);

        payStatus = getIntent().getIntExtra("PAY_STATUS",0);
        orderStatus = getIntent().getIntExtra("ORDER_STATUS",0);

        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if(payStatus == Constant.ORDER_PAY_WAIT)
            titleView.getTitleTv().setText("待付款订单");
        else if(payStatus == Constant.ORDER_PAY_ALREADY && orderStatus == Constant.ORDER_TRAVEL_WAIT)
            titleView.getTitleTv().setText("待处理订单");
        else if(payStatus == Constant.ORDER_PAY_REFUND)
            titleView.getTitleTv().setText("已取消订单");
        else if(payStatus==Constant.ORDER_PAY_ALL)
            titleView.getTitleTv().setText("总订单");

        initSwipeLayout();
        initRecycler();

        initData();

    }

    public void initData() {
        mPresenter = new OrderListPresenter(context,this);
        confirmPresenter = new OrderConfirmPresenter(context,this);
        getRefreshData();

        initRefreshDisposable();
    }

    private void initRefreshDisposable() {
        refreshDisposable = RxBus2.getInstance().toObservable(RefreshOrderListEvent.class, new Consumer<RefreshOrderListEvent>() {
            @Override
            public void accept(RefreshOrderListEvent refreshOrderListEvent) throws Exception {
                getRefreshData();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(refreshDisposable !=null && !refreshDisposable.isDisposed()){
            refreshDisposable.dispose();
        }
    }

    //初始化SwipeLayout
    public void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeColors(getResColor(R.color.color_theme));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoad) return;
                getRefreshData();
            }
        });
    }

    //初始化recyclerview
    public void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new OrderListAdapter(activity, new ArrayList<OrderListModel>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        page = Constant.DEFAULT_PAGE;

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });

        mAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int orderId,int refundId) {
                if(refundId==0){
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    intent.putExtra("ORDER_ID",orderId);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(context, OrderDetailRefundActivity.class);
                    intent.putExtra("ORDER_ID",refundId);
                    startActivity(intent);
                }
//                if(payStatus==Constant.ORDER_PAY_REFUND&&refundStatus==Constant.ORDER_REFUND_WAIT){
//
//                }else{
//
//                }
            }
        });

        mAdapter.setOnConfirmClickListener(new OrderListAdapter.OnConfirmClickListener() {
            @Override
            public void onClick(final int orderId,final String value) {
                DialogUtil.getInstance().getDefaultDialog(context, "是否确认接单", new DialogUtil.DialogCallBack() {
                    @Override
                    public void exectEvent(DialogInterface alterDialog) {
                        if(value.equals(Constant.SERVICE_TYPE_SHORT_CUSTOM)){
                            confirmPresenter.guideSurePersonalServe(orderId,1,"","");
                        }else{
                            confirmPresenter.orderSureOrder(orderId);
                        }
                    }
                }).show();
            }
        });
    }

    public void getRefreshData() {
        swipeRefreshLayout.setRefreshing(true);
        isLoad = true;
        page = Constant.DEFAULT_PAGE;
        isLoadType = 1;
        getList();
    }

    public void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        getList();
    }

    public void getList(){
        mPresenter.orderList(payStatus,orderStatus,page, Constant.DEFAULT_LIMIT,"");
    }

    @Override
    public void orderListSuccess(List<OrderListModel> models) {
        if(models==null)return;
        List<OrderListModel> datas = models;

        if (isLoadType == 1) {
            mAdapter.setData(datas);
        } else {
            mAdapter.addData(datas);
        }

        isLoad = false;
        if (datas.size() >= Constant.DEFAULT_LIMIT) {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        } else {
            // 显示加载到底的提示
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
        }

        if(swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);

        if(mAdapter.getData().size() == 0){
            emptyView.setVisible(true);
            emptyView.setEmptyData(R.mipmap.empty_order,"这里还是空空哒~");
        }else{
            emptyView.setVisible(false);
        }

    }

    @Override
    public void orderListFailed(String msg) {
        showShortToast(msg);
        isLoad = false;
        swipeRefreshLayout.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);

        if(msg.startsWith("-")){
            emptyView.setVisible(true);
            emptyView.setEmptyData(R.mipmap.empty_network, "网络竟然崩溃了", "别紧张，试试看刷新页面~", "点击刷新");
            emptyView.setBtnClickLisener(new EmptyClickLisener() {
                @Override
                public void onClick() {
                    getRefreshData();
                }
            });
        }

    }

    @Override
    public void orderSureOrderSuccess(String datas) {
        showShortToast("接单成功");
        getRefreshData();
    }

    @Override
    public void orderSureOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void guideSurePersonalServeSuccess(String datas) {
        showShortToast("接单成功");
        getRefreshData();
    }

    @Override
    public void guideSurePersonalServeFailed(String msg) {
        showShortToast(msg);
    }
}
