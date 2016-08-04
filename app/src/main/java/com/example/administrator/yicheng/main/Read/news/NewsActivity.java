package com.example.administrator.yicheng.main.Read.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.bean.Title;
import com.example.administrator.yicheng.config.Types;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jensen on 2016/8/3.
 */
public class NewsActivity extends BaseActivity implements NewsContract.View, CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener {
    @BindView(R.id.can_content_view)
    ListView canContentView;
    @BindView(R.id.fresh)
    CanRefreshLayout fresh;
    private String mid;
    private int num=0;
    private SimpleDraweeView iv_show;
    private NewsPresenter presenter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        NewsModel model=new NewsModel();
        presenter = new NewsPresenter(model,this);
        fresh.setOnLoadMoreListener(this);
        fresh.setOnRefreshListener(this);
        fresh.setStyle(0, 0);
        View view = getLayoutInflater().inflate(R.layout.head_layout, null);
        canContentView.addHeaderView(view);
        iv_show = (SimpleDraweeView) view.findViewById(R.id.iv_show);

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        Title title = (Title) intent.getSerializableExtra("title");
        iv_show.setImageURI(Uri.parse(title.getPic()));
        mid = title.getMid();
        presenter.getList(Types.HOT_TYPE,mid,num);
    }


    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }

    @OnClick(R.id.fab)
    public void onClick() {
        finish();
    }

    @Override
    public void getContentList(List<Content> list, int type) {
        Message msg=Message.obtain(handler,type);
        msg.obj=list;
        msg.sendToTarget();
    }
}
