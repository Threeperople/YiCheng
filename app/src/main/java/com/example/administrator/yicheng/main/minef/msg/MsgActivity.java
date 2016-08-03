package com.example.administrator.yicheng.main.minef.msg;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/2.
 */
public class MsgActivity extends BaseActivity {
    @BindView(R.id.msg_toolBarIcon)
    ImageView msgToolBarIcon;
    @BindView(R.id.msg_listView)
    ListView msgListView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;

    }

    @Override
    public void initView() {
        msgListView.setEmptyView(findViewById(R.id.msg_emptyView));
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.msg_toolBarIcon)
    public void onClick() {

    }
}
