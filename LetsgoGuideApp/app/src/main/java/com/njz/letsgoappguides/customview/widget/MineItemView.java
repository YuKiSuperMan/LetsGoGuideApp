package com.njz.letsgoappguides.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;


public class MineItemView extends LinearLayout {

    TextView tv_title;
    TextView tv_content;
    ImageView iv_left_img;

    public MineItemView(Context context) {
        this(context, null);
    }

    public MineItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_mine_item, this, true);

        tv_title = (TextView) findViewById(R.id.my_tvCenter);
        tv_content =  (TextView) findViewById(R.id.my_tvaCenter);
        iv_left_img = (ImageView)  findViewById(R.id.my_ivCenter);


        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.home_my_item);
        if (attributes != null) {
            String titleText = attributes.getString(R.styleable.home_my_item_title);
            if (!TextUtils.isEmpty(titleText)) {
                tv_title.setText(titleText);
            }

            String contentText = attributes.getString(R.styleable.home_my_item_content);
            if (!TextUtils.isEmpty(contentText)) {
                tv_content.setText(contentText);
            }

            int leftDrawable = attributes.getResourceId(R.styleable.home_my_item_left_drawable, -1);
            if (leftDrawable != -1) {
                iv_left_img.setImageDrawable(ContextCompat.getDrawable(context,leftDrawable));
            }
            attributes.recycle();
        }
    }

    public void setContent(String content){
        tv_content.setText(content);
    }

    public String getContent(){
        return tv_content.getText().toString();
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public String getTitle(){
        return tv_title.getText().toString();
    }

    public void setleftDrawable(Context context,int leftDrawable){
        iv_left_img.setImageDrawable(ContextCompat.getDrawable(context,leftDrawable));
    }





}
