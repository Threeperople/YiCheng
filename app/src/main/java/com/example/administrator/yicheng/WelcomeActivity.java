package com.example.administrator.yicheng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.main.MainActivity;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;

import cn.jpush.android.api.JPushInterface;

public class WelcomeActivity extends BaseActivity {

    private Boolean isFirst;
    private ImageView ImageFirst;
    private ImageView ImageSencod;


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        ImageFirst = (ImageView) findViewById(R.id.splash_first);
        ImageSencod = (ImageView) findViewById(R.id.splash_second);
        isFirst = (Boolean) SharedPreferenceUtils.get(this, "isFirst", true);
        welcomeToFirstIn();
    }

    @Override
    public void initData() {

    }

    private void welcomeToFirstIn() {
        ImageSencod.setVisibility(View.GONE);
        Animation animFirst = new AlphaAnimation(1f, 0.5f);
        animFirst.setDuration(2000);
        ImageFirst.startAnimation(animFirst);

        animFirst.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ImageFirst.setImageResource(R.mipmap.splash_second);
                ImageSencod.setVisibility(View.VISIBLE);
                Animation anim = createPanInAnim();
                ImageSencod.startAnimation(anim);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        if (isFirst) {
                            isFirst = (Boolean) SharedPreferenceUtils.get(WelcomeActivity.this, "isFirst", true);
                            SharedPreferenceUtils.remove(WelcomeActivity.this, "isFirst");
                            SharedPreferenceUtils.putAndApply(WelcomeActivity.this, "isFirst", false);
                            startActivity(new Intent(WelcomeActivity.this, FirstInActivity.class));
                            finish();
                            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                        } else {
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                            finish();
                            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

    public Animation createPanInAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // 创建一个淡入动画
        anim = new AlphaAnimation(0.5f, 1f);
        anim.setDuration(2000);
        anim.setInterpolator(new LinearInterpolator());
        ret.addAnimation(anim);
        // 创建一个放大动画

        anim = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(2000);
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);

        return ret;
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}