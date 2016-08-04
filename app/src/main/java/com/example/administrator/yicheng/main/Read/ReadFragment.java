package com.example.administrator.yicheng.main.Read;


import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.ListView;
import android.widget.Toast;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.adapter.MyListAdapter;
import com.example.administrator.yicheng.adapter.MyTitleAdapter;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.bean.Title;
import com.example.administrator.yicheng.config.Types;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReadFragment extends BaseFragment implements ReadContract.View,CanRefreshLayout.OnRefreshListener,CanRefreshLayout.OnLoadMoreListener{


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.can_content_view)
    ListView canContentView;
    @BindView(R.id.canRefresh)
    CanRefreshLayout canRefresh;
    private MyTitleAdapter titleAdapter;
    private List<Title> titles=new ArrayList<>();
    private ReadPresenter presenter;
    private List<Content> contentList=new ArrayList<>();
    private MyListAdapter listAdapter;
    private String hot;
    private static int num=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Types.TITLE_TYPE:
                    List<Title>  titlelist= (List<Title>) msg.obj;
                    titles.addAll(titlelist);
                    titleAdapter.notifyDataSetChanged();
                    break;
                case Types.HOT_TYPE:
                    List<Content> list= (List<Content>) msg.obj;
                    String msgid = list.get(0).getMsgid();
                    int i = Integer.valueOf(msgid);
                    if(contentList.size()!=0) {
                        if (msgid.equals(contentList.get(0).getMsgid())) {
                            Toast.makeText(getActivity(), "没有更新内容", Toast.LENGTH_SHORT).show();
                            canRefresh.refreshComplete();
                            break;
                        } else {
                            for (int m = 0; m <list.size(); m++) {
                                if (msgid.equals(contentList.get(m).getMsgid())) {
                                    for (int j = 0; j < m; j++) {
                                        contentList.add(j, list.get(j));
                                    }
                                } else {
                                    contentList.addAll(0, list);
                                }
                                canRefresh.refreshComplete();
                                listAdapter.notifyDataSetChanged();
                                break;
                            }
                        }
                        break;
                    }
                    num = i+1;
                    presenter.getList(Types.HOT_CONTNT_TYPE,num);
                    break;
                case Types.HOT_CONTNT_TYPE:
                    List<Content> content= (List<Content>) msg.obj;
                    contentList.addAll(content);
                    listAdapter.notifyDataSetChanged();
                    canRefresh.loadMoreComplete();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    public void initView() {
        ReadModel model=new ReadModel();
        presenter = new ReadPresenter(model,this);
        canRefresh.setOnLoadMoreListener(this);
        canRefresh.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        if(titles.size()==0) {
            presenter.getList(Types.TITLE_TYPE, num);
        }
        if(contentList.size()==0) {
          presenter.getList(Types.HOT_TYPE,num);
        }
        listAdapter = new MyListAdapter(contentList,getActivity());
        canContentView.setAdapter(listAdapter);
         titleAdapter = new MyTitleAdapter(titles, getActivity());
            rv.setAdapter(titleAdapter);
    }


    @Override
    public void onLoadMore() {
        num-=15;
        presenter.getList(Types.HOT_CONTNT_TYPE,num);
    }

    @Override
    public void onRefresh() {
        presenter.getList(Types.HOT_TYPE,num);
    }



    @Override
    public void getContentList(List<Content> list, int type) {
        Message msg=Message.obtain(handler,type);
        msg.obj=list;
        msg.sendToTarget();
    }

    @Override
    public void getTitleList(List<Title> list, int type) {
        Message msg=Message.obtain(handler,type);
        msg.obj=list;
        msg.sendToTarget();
    }
}
