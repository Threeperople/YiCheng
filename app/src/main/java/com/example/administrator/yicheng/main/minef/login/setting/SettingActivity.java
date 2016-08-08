package com.example.administrator.yicheng.main.minef.login.setting;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.RegisterPeople;
import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.example.administrator.yicheng.utils.UriFileUtils;
import com.example.administrator.yicheng.view.WheelView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.Setting_toolBarIcon)
    ImageView SettingToolBarIcon;
    @BindView(R.id.fourFragment_tv_settingImage)
    TextView fourFragmentTvSettingImage;
    @BindView(R.id.setting_name)
    LinearLayout settingName;
    @BindView(R.id.setting_sex)
    LinearLayout settingSex;
    @BindView(R.id.fourFragment_image_Icon)
    CircleImageView fourFragmentImageIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    private File file;
    private Bitmap bmp;
    private String path;
    private String sex;
    private static final String[] SEXS ={"男","保密","女"};
    private String name;
    private List<RegisterPeople> user;
    private Uri uri;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        user = LiteOrmUtils.getQueryByWhere(RegisterPeople.class, "number", new String[]{number});
        tvName.setText(user.get(0).getUserName());
        tvSex.setText(user.get(0).getSex());
        String uri = user.get(0).getUri();
        if(uri!=null) {
            ContentResolver cr = this.getContentResolver();
            try {
                fourFragmentImageIcon.setImageBitmap(BitmapFactory.decodeStream(cr.openInputStream(Uri.fromFile(new File(uri)))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.Setting_toolBarIcon, R.id.fourFragment_image_Icon, R.id.fourFragment_tv_settingImage, R.id.setting_name, R.id.setting_sex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Setting_toolBarIcon:
                RegisterPeople registerPeople = user.get(0);
                if(sex!=null) {
                    registerPeople.setSex(sex);
                }
                if(name!=null) {
                    registerPeople.setUserName(name);
                }
                if(uri!=null){
                    registerPeople.setUri(UriFileUtils.getImageAbsolutePath(this,uri));
                }
                LiteOrmUtils.update(registerPeople);
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                break;
            case R.id.fourFragment_image_Icon:
            case R.id.fourFragment_tv_settingImage:
                final CharSequence[] items = {"照相机", "相册"};
                new AlertDialog.Builder(this).setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[0].equals(items[which])) {
                            long time = System.currentTimeMillis();
                            int m = (int) (time / 10000);
                            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + m + ".jpg";
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            file = new File(path);
                            //指定了照片的存储路径
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            startActivityForResult(intent, 0);

                        } else if (items[1].equals(items[which])) {

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
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialog_edittext, null);
                dialog.setView(layout);
                final EditText et_name = (EditText) layout.findViewById(R.id.ed_name);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = et_name.getText().toString().trim();
                        if(!TextUtils.isEmpty(name)){
                            tvName.setText(name);
                        }
                    }
                }).setNegativeButton("取消", null).show();

                break;
            case R.id.setting_sex:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(0);
                wv.setSeletion(1);
                wv.setItems(Arrays.asList(SEXS));
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener(){
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        sex=SEXS[selectedIndex];
                    }
                });
                new AlertDialog.Builder(this)
                        .setTitle("性别")
                        .setView(outerView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                  tvSex.setText(sex);
                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //选择图片
            ContentResolver cr = this.getContentResolver();
            if (requestCode == 1) {
                uri = data.getData();
            } else if (requestCode == 0) {
                uri = Uri.fromFile(file);
            }
            try {
                if (bmp != null)//如果不释放的话，不断取图片，将会内存不够
                    bmp.recycle();
                bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("the bmp toString: " + bmp);
            fourFragmentImageIcon.setImageBitmap(bmp);
        } else {
            Toast.makeText(SettingActivity.this, "请重新选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    public void cropImage(Uri uri, int outputX, int outputY, int requestCode) {
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
