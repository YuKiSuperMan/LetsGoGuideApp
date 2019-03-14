package com.njz.letsgoappguides.adapter.server;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.mine.DynamicAdapter;
import com.njz.letsgoappguides.customview.widget.PriceView;
import com.njz.letsgoappguides.model.server.ServerListModel;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ViewHolder> {


    Context context;
    List<ServerListModel> datas;

    public ServerListAdapter(Context context, List<ServerListModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_server, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder == null) return;
        final int pos = holder.getAdapterPosition();
        final ServerListModel data = datas.get(pos);
        if (data == null) return;

        if (!TextUtils.isEmpty(data.getTitleImg())) {
            String titleImg = data.getTitleImg();
            ArrayList<String> list = StringUtils.stringToList(titleImg);
            GlideUtil.LoadRoundImage(context, list.get(0), holder.iv_img, 0);
        }
        holder.tv_server_name.setText(data.getTitle());
        holder.tv_server_status.setText(data.getStatusStr());
        holder.tv_location.setText(data.getAddress());
        holder.tv_server_type.setText(data.getServeTypeName());
        holder.pv_price.setPrice(data.getServePrice());

        holder.tv_delete.setVisibility(View.VISIBLE);
        holder.tv_down.setVisibility(View.GONE);
        holder.tv_up.setVisibility(View.GONE);
        holder.tv_modify.setVisibility(View.GONE);
        holder.tv_copy.setVisibility(View.GONE);
        holder.tv_verify_failed.setVisibility(View.GONE);

        switch (data.getStatus()) {//1上架  0下架  -1强制下架  2审核中  3审核未通过
            case 0:
                holder.tv_server_status.setTextColor(ContextCompat.getColor(context, R.color.color_theme));
                holder.tv_up.setVisibility(View.VISIBLE);
                holder.tv_modify.setVisibility(View.VISIBLE);
                holder.tv_copy.setVisibility(View.VISIBLE);
                holder.cardView.setBackgroundResource(R.drawable.card_view_bg_10);
                break;
            case 1:
                holder.tv_server_status.setTextColor(ContextCompat.getColor(context, R.color.color_green));
                holder.tv_down.setVisibility(View.VISIBLE);
                holder.tv_modify.setVisibility(View.VISIBLE);
                holder.tv_copy.setVisibility(View.VISIBLE);
                holder.cardView.setBackgroundResource(R.drawable.card_view_bg_10);
                break;
            case 2:
                holder.tv_server_status.setTextColor(ContextCompat.getColor(context, R.color.color_theme));
                holder.tv_down.setVisibility(View.VISIBLE);
                holder.tv_modify.setVisibility(View.VISIBLE);
                holder.tv_copy.setVisibility(View.VISIBLE);
                holder.cardView.setBackgroundResource(R.drawable.card_view_bg_10);
                break;
            case 3:
                holder.tv_server_status.setTextColor(ContextCompat.getColor(context, R.color.color_red));
                holder.tv_down.setVisibility(View.VISIBLE);
                holder.tv_modify.setVisibility(View.VISIBLE);
                holder.tv_copy.setVisibility(View.VISIBLE);
                holder.cardView.setBackgroundResource(R.drawable.card_view_bg_10);

                holder.tv_verify_failed.setVisibility(View.VISIBLE);
                holder.tv_verify_failed.setText(data.getCantPassReason());
                break;
            case -1:
                holder.cardView.setBackgroundResource(R.drawable.card_view_bg_10_gray);
                holder.tv_server_status.setTextColor(ContextCompat.getColor(context, R.color.color_red));
                break;
        }

        holder.tv_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(data.getId());
            }
        });

        holder.tv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//上架
                mOnItemClickListener.onItemClickToUp(data.getId(), data.getServePrice());
            }
        });

        holder.tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//下架
                mOnItemClickListener.onItemClickToDown(data.getId(), data.getServePrice());
            }
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {//删除
            @Override
            public void onClick(View v) {//删除
                mOnItemClickListener.onItemClickDelete(data.getId());
            }
        });

        holder.ll_deail.setOnClickListener(new View.OnClickListener() {//预览
            @Override
            public void onClick(View v) {//预览
                mOnItemClickListener.onItemClickPreview(data.getId());
            }
        });

        holder.tv_copy.setOnClickListener(new View.OnClickListener() {//复制
            @Override
            public void onClick(View v) {//复制
                mOnItemClickListener.onItemClickCopy(data.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void addData(List<ServerListModel> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setData(List<ServerListModel> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<ServerListModel> getDatas() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout cardView;
        ImageView iv_img;
        TextView tv_server_name, tv_server_status, tv_location, tv_server_type, tv_verify_failed;
        PriceView pv_price;
        TextView tv_delete, tv_down, tv_up, tv_modify, tv_copy;
        LinearLayout ll_deail;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_server_name = itemView.findViewById(R.id.tv_server_name);
            tv_server_status = itemView.findViewById(R.id.tv_server_status);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_server_type = itemView.findViewById(R.id.tv_server_type);
            pv_price = itemView.findViewById(R.id.pv_price);
            tv_delete = itemView.findViewById(R.id.tv_delete);
            tv_down = itemView.findViewById(R.id.tv_down);
            tv_up = itemView.findViewById(R.id.tv_up);
            tv_modify = itemView.findViewById(R.id.tv_modify);
            tv_verify_failed = itemView.findViewById(R.id.tv_verify_failed);
            ll_deail = itemView.findViewById(R.id.ll_deail);
            tv_copy = itemView.findViewById(R.id.tv_copy);
        }
    }

    //---------事件 start---------
    ServerListAdapter.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int njzGuideServeId);

        void onItemClickDelete(int njzGuideServeId);

        void onItemClickToUp(int njzGuideServeId, float price);

        void onItemClickToDown(int njzGuideServeId, float price);

        void onItemClickPreview(int njzGuideServeId);

        void onItemClickCopy(int njzGuideServeId);
    }

    public void setOnItemClickListener(ServerListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    ServerListAdapter.OnDetailClickListener onDetailClickListener;

    public interface OnDetailClickListener {
        void onItemClick(int orderId);
    }

    public void setOnDetailClickListener(ServerListAdapter.OnDetailClickListener onDetailClickListener) {
        this.onDetailClickListener = onDetailClickListener;
    }

    //---------事件 end---------
}
