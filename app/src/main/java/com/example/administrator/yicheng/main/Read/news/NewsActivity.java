package com.example.administrator.yicheng.main.Read.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.adapter.MyListAdapter;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.bean.Title;
import com.example.administrator.yicheng.config.Types;
import com.example.administrator.yicheng.view.PullToZoomListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jensen on 2016/8/3.
 */
public class NewsActivity extends BaseActivity implements NewsContract.View, CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener {
    @BindView(R.id.can_content_view)
    PullToZoomListView canContentView;
    @BindView(R.id.fresh)
    CanRefreshLayout fresh;
    private List<Content> ContentList=new ArrayList<>();
    private String mid;
    private int num=0;
    private NewsPresenter presenter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Types.HOT_TYPE:
                 List<Content> list= (List<Content>) msg.obj;
                 String msgid=list.get(0).getMsgid();
                    int i=Integer.valueOf(msgid);
                    if(ContentList.size()!=0) {
                        if (msgid.equals(list.get(0).getMsgid())) {
                            Toast.makeText(NewsActivity.this, "没有更新内容", Toast.LENGTH_SHORT).show();
                            fresh.refreshComplete();
                            break;
                        } else {
                            for (int m = 0; m < list.size(); m++) {
                                if (msgid.equals(ContentList.get(m).getMsgid())) {
                                    for (int j = 0; j < m; j++) {
                                        ContentList.add(j, list.get(j));
                                    }
                                } else {
                                    ContentList.addAll(0,list);
                                }
                                fresh.refreshComplete();
                                adapter.notifyDataSetChanged();
                                break;
                            }
                        }
                        break;
                    }
                    num = i+1;
                    presenter.getList(Types.HOT_CONTNT_TYPE,mid,num);
                    break;
                case Types.HOT_CONTNT_TYPE:
                    List<Content> content = (List<Content>) msg.obj;
                    ContentList.addAll(content);
                    adapter.notifyDataSetChanged();
                    fresh.loadMoreComplete();
                    break;
            }
        }
    };
    private MyListAdapter adapter;

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
        fresh.setStyle(1, 0);
        adapter = new MyListAdapter(ContentList,this);
        canContentView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        Title title = (Title) intent.getSerializableExtra("title");
        canContentView.getHeaderView().setImageURI(Uri.parse(title.getPic()));
        mid = title.getMid();
        presenter.getList(Types.HOT_TYPE,mid,num);
    }


    @Override
    public void onLoadMore() {
        String msgid = ContentList.get(ContentList.size() - 1).getMsgid();
        presenter.getList(Types.HOT_CONTNT_TYPE,mid,Integer.valueOf(msgid));
    }

    @Override
    public void onRefresh() {
       presenter.getList(Types.HOT_TYPE,mid,num);
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
        Log.i("TAG", "getContentList: "+list.get(0).getMtime());
    }
}
