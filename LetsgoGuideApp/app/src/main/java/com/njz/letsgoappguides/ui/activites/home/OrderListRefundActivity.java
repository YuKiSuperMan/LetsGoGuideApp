package com.njz.letsgoappguides.ui.activites.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.EndlessRecyclerOnScrollListener;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.home.OrderRefundListAdapter;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyClickLisener;
import com.njz.letsgoappguides.model.home.OrderRefundModel;
import com.njz.letsgoappguides.presenter.home.OrderRefundListContract;
import com.njz.letsgoappguides.presenter.home.OrderRefundListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderListRefundActivity extends OrderListActivity implements OrderRefundListContract.View {

    OrderRefundListPresenter refundPresenter;
    OrderRefundListAdapter mAdapter;

    @Override
    public void initData() {
        titleView.getTitleTv().setText("退款单");

        refundPresenter = new OrderRefundListPresenter(context, this);
        getRefreshData();
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new OrderRefundListAdapter(activity, new ArrayList<OrderRefundModel>());
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

        mAdapter.setOnItemClickListener(new OrderRefundListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int ids,int payStatus,int orderId) {//ids==0已取消   退款
                if(ids==0){
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    intent.putExtra("ORDER_ID",orderId);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(context, OrderDetailRefundActivity.class);
                    intent.putExtra("ORDER_ID",ids);
                    intent.putExtra("PAYSTATUS",payStatus);
                    startActivity(intent);
                }
            }
        });

        mAdapter.setOnConfirmClickListener(new OrderRefundListAdapter.OnConfirmClickListener() {
            @Override
            public void onClick(final int orderId) {//确认退款

                Intent intent = new Intent(context, OrderRefundActivity.class);
                intent.putExtra("REFUND_ID",orderId);
                startActivity(intent);
            }
        });

    }

    public void getList() {
        refundPresenter.orderRefundList(page, Constant.DEFAULT_LIMIT,  "");
    }

    @Override
    public void orderRefundListSuccess(List<OrderRefundModel> data) {
        List<OrderRefundModel> datas = data;
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
        swipeRefreshLayout.setRefreshing(false);

        if(mAdapter.getData().size() == 0){
            emptyView.setVisible(true);
            emptyView.setEmptyData(R.mipmap.empty_order,"这里还是空空哒~");
        }else{
            emptyView.setVisible(false);
        }

    }

    @Override
    public void orderRefundListFailed(String msg) {
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
}
