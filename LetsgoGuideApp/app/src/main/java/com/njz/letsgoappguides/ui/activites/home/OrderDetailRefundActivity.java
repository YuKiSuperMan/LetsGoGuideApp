package com.njz.letsgoappguides.ui.activites.home;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.home.OrderDetailAdapter;
import com.njz.letsgoappguides.adapter.home.OrderRefundDetailAdapter;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderRefundDetailChildModel;
import com.njz.letsgoappguides.model.home.OrderRefundDetailModel;
import com.njz.letsgoappguides.presenter.home.OrderRefundDetailContract;
import com.njz.letsgoappguides.presenter.home.OrderRefundDetailPresenter;
import com.njz.letsgoappguides.ui.im.ChatActivity;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.dialog.RemarkDialog;

import java.util.ArrayList;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public class OrderDetailRefundActivity extends OrderDetailActivity implements OrderRefundDetailContract.View{

    OrderRefundDetailPresenter refundPresenter;
    OrderRefundDetailAdapter refundAdapter;

    OrderRefundDetailModel refundModel;

    RelativeLayout rl_refund_penalty,rl_refund_price,rl_order_price,rl_refund_used_price;
    TextView tv_refund_penalty,tv_refund_price,tv_refund_used_price;
    FrameLayout cv_refund_reason;
    TextView tv_refund_reason,tv_refund_explain;


    @Override
    public void initData() {
        refundPresenter = new OrderRefundDetailPresenter(context,this);
        refundPresenter.orderQueryRefundOrder(orderId);
    }

    @Override
    public void initView() {
        super.initView();
        rl_refund_penalty = $(R.id.rl_refund_penalty);
        rl_refund_price = $(R.id.rl_refund_price);
        rl_order_price = $(R.id.rl_order_price);
        rl_refund_used_price = $(R.id.rl_refund_used_price);
        tv_refund_used_price = $(R.id.tv_refund_used_price);
        tv_refund_penalty = $(R.id.tv_refund_penalty);
        tv_refund_price = $(R.id.tv_refund_price);
        cv_refund_reason = $(R.id.cv_refund_reason);
        tv_refund_reason = $(R.id.tv_refund_reason);
        tv_refund_explain = $(R.id.tv_refund_explain);

        rl_order_price.setVisibility(View.GONE);
        rl_refund_used_price.setVisibility(View.GONE);
        rl_refund_price.setVisibility(View.VISIBLE);
        rl_refund_penalty.setVisibility(View.VISIBLE);
        cv_refund_reason.setVisibility(View.VISIBLE);
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        refundAdapter = new OrderRefundDetailAdapter(activity, new ArrayList<OrderRefundDetailChildModel>());
        recyclerView.setAdapter(refundAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_call:
                DialogUtil.getInstance().showGuideMobileDialog(context,refundModel.getMobile());
                break;
            case R.id.btn_refund:
                intent = new Intent(context, OrderRefundActivity.class);
                intent.putExtra("REFUND_ID",orderId);
                startActivity(intent);
                break;
            case R.id.ll_order_remarks:
            case R.id.iv_order_remarks:
            case R.id.tv_order_remarks:
            case R.id.iv_order_remarks_edit:
                RemarkDialog remarkDialog=new RemarkDialog(this,refundModel.getId(),ll_order_remarks, ll_order_remarks_edit,tv_order_remarks_context);
                remarkDialog.show();
                break;
        }

    }

    @Override
    public void orderQueryRefundOrderSuccess(final OrderRefundDetailModel str) {
        refundModel = str;

        ll_order_no.setVisibility(View.VISIBLE);
        tv_order_no.setText(str.getOrderNo());
        ll_order_create_time.setVisibility(View.VISIBLE);
        ll_order_pay_time.setVisibility(View.VISIBLE);
        ll_order_pay_method.setVisibility(View.VISIBLE);
        tv_order_create_time.setText(str.getCreateTime());
        tv_order_pay_time.setText(str.getPayTime());
        tv_order_pay_method.setText(str.getPayType());

        ll_order_refund_apply.setVisibility(View.VISIBLE);
        tv_order_refund_apply.setText(str.getApplyTime());

        btn_view_desingn_scheme.setVisibility(View.GONE);
        login_view_name.setContent(str.getName());
        login_view_phone.setContent(str.getMobile());

        switch (str.getRefundStatus()){
            case Constant.ORDER_REFUND_WAIT:
                //底部按钮
                btn_call.setVisibility(View.VISIBLE);
                btn_refund.setVisibility(View.VISIBLE);

                //倒计时
                tv_countdown.setVisibility(View.VISIBLE);
                tv_countdown.setText(str.getSureTime());

                isShowRemarks(str.getOrderNote());
                break;
            case Constant.ORDER_REFUND_PROCESS:
                //底部按钮
//                ll_bottom.setVisibility(View.GONE);
                isShowNoEdit(str.getOrderNote());
                break;
            case Constant.ORDER_REFUND_FINISH:
                //底部按钮
//                ll_bottom.setVisibility(View.GONE);

                isShowNoEdit(str.getOrderNote());
                //订单状态
                ll_order_refund_time.setVisibility(View.VISIBLE);
                tv_order_refund_time.setText(str.getRefundTime());

                login_view_phone.setImgVisibility(View.GONE);

                login_view_phone.setContent(str.getMobilehide());
                login_view_name.setContent(str.getNameHide());
                break;
        }

        //信息
        tv_guide_name.setText(str.getOrderNo());
        tv_order_status.setText(str.getPayStatusStr());
        fixed_view_personl.setContent(str.getPersonNumStr1());

        login_view_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.getInstance().showGuideMobileDialog(context,str.getMobile());
            }
        });
        et_special.setContent(TextUtils.isEmpty(str.getSpecialRequire())?"无":str.getSpecialRequire());

        tv_refund_penalty.setText(str.getDefaultMoney() + "");
        tv_refund_price.setText(str.getRefundMoney() + "");

        //已消费金额
        boolean isTravelGoing = false;
        for(int i = 0;i<str.getNjzRefundDetailsChildVOS().size();i++){
            if(str.getNjzRefundDetailsChildVOS().get(i).getChildOrderStatus() == Constant.ORDER_TRAVEL_GOING){
                isTravelGoing = true;
            }
        }
        if(isTravelGoing){
            rl_refund_used_price.setVisibility(View.VISIBLE);
            tv_refund_used_price.setText(str.getUseMoney()+"");
        }else{
            rl_refund_used_price.setVisibility(View.GONE);
        }

        tv_refund_reason.setText(str.getRefundReason());
        tv_refund_explain.setText(str.getRefundContent());

        if(str.getNjzRefundDetailsChildVOS().size()>0) {
            if (str.getNjzRefundDetailsChildVOS().get(0).getValue().equals(Constant.SERVICE_TYPE_SHORT_CUSTOM)) {
                ll_bottom.setVisibility(View.VISIBLE);
                fixed_view_personl.setContent(str.getPersonNumStr2());
                btn_view_desingn_scheme.setVisibility(View.VISIBLE);
            }
        }

        ll_order_remarks.setOnClickListener(this);
        iv_order_remarks.setOnClickListener(this);
        tv_order_remarks.setOnClickListener(this);
        iv_order_remarks_edit.setOnClickListener(this);

        btn_view_desingn_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//查看方案
                Intent intent = new Intent(context,CustomPlanActivity.class);
                intent.putExtra("ORDER_ID",str.getOrderId());
                intent.putExtra("GUIDE_PHONE",str.getMobile());
                context.startActivity(intent);
            }
        });

        btn_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MySelfInfo.getInstance().getImLogin()){
                    showShortToast("用户未注册到im");
                    return;
                }

                if(refundModel == null) return;
                String name = "u_"+ refundModel.getUserId();
                String myName = EMClient.getInstance().getCurrentUser();
                if (!TextUtils.isEmpty(name)) {
                    if (name.equals(myName)) {
                        showShortToast("不能和自己聊天");
                        return;
                    }
                    Intent chat = new Intent(context, ChatActivity.class);
                    chat.putExtra(EaseConstant.EXTRA_USER_ID, name);  //对方账号
                    chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                    startActivity(chat);

                } else {
                    showShortToast("游客还未注册即时通讯，请使用电话联系TA");
                }
            }
        });

        refundAdapter.setData(str.getNjzRefundDetailsChildVOS());
    }

    @Override
    public void orderQueryRefundOrderFailed(String msg) {
        showShortToast(msg);
    }
}
