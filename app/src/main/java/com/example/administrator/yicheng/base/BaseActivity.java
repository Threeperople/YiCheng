package com.example.administrator.yicheng.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;

import com.example.administrator.yicheng.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/8/1.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private Unbinder bind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        initView();
        initData();


    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
