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

    float xDown=0;
    float yDown=0;
    float xUp=0;
    float yUp=0;

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
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
