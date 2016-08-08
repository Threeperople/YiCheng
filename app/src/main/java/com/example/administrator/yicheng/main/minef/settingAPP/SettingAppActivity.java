package com.example.administrator.yicheng.main.minef.settingAPP;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.config.Flags;
import com.example.administrator.yicheng.main.minef.settingAPP.aboutus.AboutUsActivity;
import com.example.administrator.yicheng.utils.CacheCleanUtils;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;
import com.example.administrator.yicheng.view.DashSpinner;
import com.leaking.slideswitch.SlideSwitch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/8/3.
 */
public class SettingAppActivity extends BaseActivity implements DashSpinner.OnDownloadIntimationListener {
    @BindView(R.id.settingApp_toolBarIcon)
    ImageView settingAppToolBarIcon;
    @BindView(R.id.slideSwitch_setting)
    SlideSwitch slideSwitchSetting;
    @BindView(R.id.settingapp_deleteCache)
    LinearLayout settingappDeleteCache;
    @BindView(R.id.settingapp_checkToRefresh)
    LinearLayout settingappCheckToRefresh;
    @BindView(R.id.settingapp_scoreToApp)
    LinearLayout settingappScoreToApp;
    @BindView(R.id.settingapp_about)
    LinearLayout settingappAbout;
    @BindView(R.id.setting_exitApp)
    TextView settingExitApp;
    @BindView(R.id.settingApp_fileSize)
    TextView settingAppFileSize;
    @BindView(R.id.settingApp_progress_spinner)
    DashSpinner settingAppProgressSpinner;
    @BindView(R.id.settingApp_refreshImage)
    ImageView settingAppRefreshImage;
    @BindView(R.id.settingApp_refresh_Tv)
    TextView settingAppRefreshTv;
    @BindView(R.id.settingApp_refresh_exitMark)
    ImageView settingAppRefreshExitMark;


    private Handler mHandler = new Handler();

    private String cacheSize;
    float mnProgress = 0.0f;
    Boolean openFlag = true;
    Runnable runnableSuccess = new Runnable() {
        @Override
        public void run() {
            try {
                setProgress();
                CacheCleanUtils.cleanInternalCache(SettingAppActivity.this);
                if (mnProgress <= 1.0) {
                    mHandler.postDelayed(this, 20);
                } else {
                    settingFileSize();
                    settingAppProgressSpinner.showSuccess();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_settingapp;

    }

    @Override
    public void initView() {
        setttingAccept();//根据sharepreference 中存的键值对的值，来设置按钮的状态
        settingFileSize();//获取缓存文件的大小
        isLogIn();

        settingAppProgressSpinner.setOnDownloadIntimationListener(this);

        //滑动按钮控制推送关闭与开启
        slideSwitchSetting.setSlideListener(new SlideSwitch.SlideListener() {
            @Override
            public void open() {//开启推送
                openFlag=true;
                SharedPreferenceUtils.putAndApply(SettingAppActivity.this, Flags.IsAcceptMsgFlag, true);
            }

            @Override
            public void close() {//关闭推送
                openFlag=false;
                Boolean  o = (Boolean) SharedPreferenceUtils.get(SettingAppActivity.this, Flags.IsAcceptMsgFlag, false);
                if(o==true){
                    SharedPreferenceUtils.remove(SettingAppActivity.this,Flags.IsAcceptMsgFlag);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(openFlag){
            JPushInterface.resumePush(SettingAppActivity.this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!openFlag){
            JPushInterface.onPause(SettingAppActivity.this);
        }
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.settingApp_toolBarIcon, R.id.slideSwitch_setting, R.id.settingapp_deleteCache, R.id.settingapp_checkToRefresh, R.id.settingapp_scoreToApp, R.id.settingapp_about, R.id.setting_exitApp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settingApp_toolBarIcon:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                break;
            case R.id.settingapp_deleteCache://清除缓存

                settingAppProgressSpinner.setVisibility(View.VISIBLE);
                settingAppProgressSpinner.resetValues();
                mnProgress = 0;//初始化
                mHandler.post(runnableSuccess);
                break;

            case R.id.settingapp_checkToRefresh://点击检查版本更新
                startAnimotionForRefresh();
                break;

            case R.id.settingapp_scoreToApp://为我们打分
//                Intent intent1 = new Intent("android.intent.action.MAIN");
//
//                intent1.addCategory("android.intent.category.APP_MARKET");
//                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent1);
                Uri uri = Uri.parse("market://details?id="+ "com.ours.weizhi");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;

            case R.id.settingapp_about://关于我们
                startActivity(new Intent(this, AboutUsActivity.class));
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;

            case R.id.setting_exitApp://如果登录就退出，未登录按钮无法点击
                Boolean logIn = (Boolean) SharedPreferenceUtils.get(this, Flags.IsLogInFlag, false);
                    if(logIn==true){
                        SharedPreferenceUtils.remove(this, Flags.IsLogInFlag);
                        Intent intent = new Intent("exitLogIn");
                        intent.putExtra("exit", "exit");
                        sendBroadcast(intent);
                        settingExitApp.setAlpha(122);
                        settingExitApp.setEnabled(false);
                    }
                break;
        }
    }

    private void isLogIn() {
        Boolean logIn = (Boolean) SharedPreferenceUtils.get(this, Flags.IsLogInFlag, false);
        if (logIn != null) {
            if (logIn == true) {
                settingExitApp.setAlpha(254);
                settingExitApp.setEnabled(true);
            }
        } else {
            settingExitApp.setAlpha(122);
            settingExitApp.setEnabled(false);
        }
    }


    private void setProgress() {
        mnProgress += 0.01;
        settingAppProgressSpinner.setProgress(mnProgress);
    }

    private void settingFileSize() {
        try {
            cacheSize = CacheCleanUtils.getCacheSize(this.getCacheDir());
            settingAppFileSize.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void setttingAccept() {
        Boolean accept = (Boolean) SharedPreferenceUtils.get(SettingAppActivity.this, Flags.IsAcceptMsgFlag, false);
        if (accept) {
            slideSwitchSetting.moveToDest(true);
        } else {
            slideSwitchSetting.moveToDest(false);
        }

    }
    private void startAnimotionForRefresh() {
        settingAppRefreshImage.setVisibility(View.VISIBLE);
        RotateAnimation rotateAnimation = new RotateAnimation(0,720
                ,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        rotateAnimation.setDuration(3000);
        settingAppRefreshImage.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                settingAppRefreshTv.setVisibility(View.GONE);
                settingAppRefreshExitMark.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settingAppRefreshTv.setVisibility(View.VISIBLE);
                settingAppRefreshExitMark.setVisibility(View.VISIBLE);
                Toast.makeText(SettingAppActivity.this, "已是最新版本！", Toast.LENGTH_SHORT).show();
                settingAppRefreshImage.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
    //清除缓存时，控件动画监听
    @Override
    public void onDownloadIntimationDone(DashSpinner.DASH_MODE dashMode) {
        switch (dashMode) {
            case SUCCESS:
                settingAppProgressSpinner.setVisibility(View.GONE);
                Toast.makeText(this, "Delete Compelte!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
