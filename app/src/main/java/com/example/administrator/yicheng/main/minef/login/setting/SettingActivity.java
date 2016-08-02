package com.example.administrator.yicheng.main.minef.login.setting;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.Setting_toolBarIcon)
    ImageView SettingToolBarIcon;
    @BindView(R.id.fourFragment_image_Icon)
    ImageView fourFragmentImageIcon;
    @BindView(R.id.fourFragment_tv_settingImage)
    TextView fourFragmentTvSettingImage;
    @BindView(R.id.setting_name)
    LinearLayout settingName;
    @BindView(R.id.setting_sex)
    LinearLayout settingSex;

    private Bitmap bmp;
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Setting_toolBarIcon, R.id.fourFragment_image_Icon, R.id.fourFragment_tv_settingImage, R.id.setting_name, R.id.setting_sex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Setting_toolBarIcon:
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
                break;
            case R.id.fourFragment_image_Icon:
            case R.id.fourFragment_tv_settingImage:
                final CharSequence[] items = {"照相机", "相册"};
                    new AlertDialog.Builder(this).setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(items[0].equals(items[which])){

                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 0);

                                }else  if(items[1].equals(items[which])){

                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "选择图片"), 1);
                            }
                        }
                    }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();

                break;
            case R.id.setting_name:

                break;
            case R.id.setting_sex:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            //选择图片
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                if(bmp != null)//如果不释放的话，不断取图片，将会内存不够
                    bmp.recycle();
                bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("the bmp toString: " + bmp);
            fourFragmentImageIcon.setImageBitmap(bmp);
        }else{
            Toast.makeText(SettingActivity.this, "请重新选择图片", Toast.LENGTH_SHORT).show();
        }

    }

    public void cropImage(Uri uri, int outputX, int outputY, int requestCode){
        //裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        //图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, requestCode);
    }
}
