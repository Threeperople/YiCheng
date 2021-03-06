package com.example.administrator.yicheng.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yicheng.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/8/1.
 */
public abstract class BaseFragment extends Fragment implements IBaseView{

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container,false);
        bind = ButterKnife.bind(this, view);
        initView();
        initData();

        return view;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();



    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}
