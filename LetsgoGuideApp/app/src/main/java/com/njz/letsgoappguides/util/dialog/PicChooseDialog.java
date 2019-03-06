package com.njz.letsgoappguides.util.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.wildma.idcardcamera.camera.CameraActivity;


public class PicChooseDialog extends Dialog implements View.OnClickListener {
    Activity mActivity;
    Button btClose;
    Button tbChoosePic;
    Button btTackPic;

    Uri cameraUri;
    int type_camera;


    public PicChooseDialog(Activity activity,Uri cameraUri) {
        super(activity);
        mActivity = activity;

        this.cameraUri = cameraUri;
    }

    public PicChooseDialog(Activity activity,Uri cameraUri,int type_camera) {
        super(activity);
        mActivity = activity;

        this.cameraUri = cameraUri;
        this.type_camera=type_camera;
    }

    public PicChooseDialog(Activity activity, int theme) {
        super(activity, theme);
        mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_pic_choose, null);
        this.setContentView(layout);

        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        btClose = layout.findViewById(R.id.bt_close);
        tbChoosePic = layout.findViewById(R.id.tb_choose_pic);
        btTackPic = layout.findViewById(R.id.bt_tack_pic);

        btClose.setOnClickListener(this);
        tbChoosePic.setOnClickListener(this);
        btTackPic.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.bt_close:
                dismiss();
                break;
            case R.id.tb_choose_pic:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mActivity.startActivityForResult(intent, TackPicturesUtil.CHOOSE_PIC);
                dismiss();
                break;
            case R.id.bt_tack_pic:
                if(type_camera==0){
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,cameraUri);
                    mActivity.startActivityForResult(intent, TackPicturesUtil.TACK_PIC);
                    dismiss();
                    break;
                }else{
                    switch (type_camera){
                        case CameraActivity.TYPE_IDCARD_PERSON:
                            CameraActivity.toCameraActivity(mActivity, CameraActivity.TYPE_IDCARD_PERSON);
                            dismiss();
                            break;
                        case CameraActivity.TYPE_IDCARD_FRONT:
                            CameraActivity.toCameraActivity(mActivity, CameraActivity.TYPE_IDCARD_FRONT);
                            dismiss();
                            break;
                        case CameraActivity.TYPE_IDCARD_BACK:
                            CameraActivity.toCameraActivity(mActivity, CameraActivity.TYPE_IDCARD_BACK);
                            dismiss();
                            break;
                    }
                }
        }
    }
}
