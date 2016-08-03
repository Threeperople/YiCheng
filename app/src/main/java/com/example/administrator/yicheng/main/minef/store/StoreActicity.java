package com.example.administrator.yicheng.main.minef.store;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/3.
 */
public class StoreActicity extends BaseActivity {

    @BindView(R.id.store_toolBarIcon)
    ImageView storeToolBarIcon;
    @BindView(R.id.store_listView)
    ListView storeListView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_store;

    }

    @Override
    public void initView() {
        storeListView.setEmptyView(findViewById(R.id.store_emptyView));
    }

    @Override
    public void initData() {

    }




    @OnClick(R.id.store_toolBarIcon)
    public void onClick() {
    }
}
