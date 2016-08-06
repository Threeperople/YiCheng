package com.example.administrator.yicheng.main.Read;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.adapter.MyListAdapter;
import com.example.administrator.yicheng.adapter.MyTitleAdapter;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.City;
import com.example.administrator.yicheng.bean.CityTitle;
import com.example.administrator.yicheng.bean.CityTitleBean;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.bean.Title;
import com.example.administrator.yicheng.config.Types;
import com.example.administrator.yicheng.config.Urls;
import com.example.administrator.yicheng.main.Read.location.LocationActivity;
import com.example.administrator.yicheng.utils.BdlocationUtils;
import com.google.gson.Gson;



import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

import butterknife.OnClick;

public class ReadFragment extends BaseFragment implements ReadContract.View, CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.can_content_view)
    ListView canContentView;
    @BindView(R.id.canRefresh)
    CanRefreshLayout canRefresh;
    @BindView(R.id.city)
    TextView tv_city;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.add)
    ImageView add;
    private MyTitleAdapter titleAdapter;
    private List<Title> titles = new ArrayList<>();
    private ReadPresenter presenter;
    private List<Content> contentList = new ArrayList<>();
    private MyListAdapter listAdapter;
    private String hot;
    private String cityName;
    private static int num = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Types.TITLE_TYPE:
                    List<Title> titlelist = (List<Title>) msg.obj;
                    titles.addAll(titlelist);
                    titleAdapter.notifyDataSetChanged();
                    break;
                case Types.HOT_TYPE:
                    List<Content> list = (List<Content>) msg.obj;
                    String msgid = list.get(0).getMsgid();
                    int i = Integer.valueOf(msgid);
                    if (contentList.size() != 0) {
                        if (msgid.equals(contentList.get(0).getMsgid())) {
                            Toast.makeText(getActivity(), "没有更新内容", Toast.LENGTH_SHORT).show();
                            canRefresh.refreshComplete();
                            break;
                        } else {
                            for (int m = 0; m < list.size(); m++) {
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
                    num = i + 1;
                    presenter.getList(Types.HOT_CONTNT_TYPE, num);
                    break;
                case Types.HOT_CONTNT_TYPE:
                    List<Content> content = (List<Content>) msg.obj;
                    contentList.addAll(content);
                    listAdapter.notifyDataSetChanged();
                    canRefresh.loadMoreComplete();
                    break;
            }
        }
    };
    private MyReceiver receiver;
    private String cityCode=null;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    public void initView() {
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("city");
        getActivity().registerReceiver(receiver, filter);
        ReadModel model = new ReadModel();
        presenter = new ReadPresenter(model, this);
        canRefresh.setOnLoadMoreListener(this);
        canRefresh.setOnRefreshListener(this);
        canRefresh.setStyle(1, 0);
        BdlocationUtils.getLocation(getActivity());
        BdlocationUtils.setMyLocationListener(new BdlocationUtils.MyLocationListener() {

                @Override
                public void myLocatin(double mylongitude, double mylatitude, String city, String street) {
                    cityName = city;
                    tv_city.setText(city);
                }
            });

    }

    @Override
    public void initData() {
        Gson gson=new Gson();
        CityTitleBean cityTitleBean = gson.fromJson(Urls.CITYJSON, CityTitleBean.class);
        List<CityTitle> titles = cityTitleBean.getData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        if (this.titles.size() == 0) {
            presenter.getList(Types.TITLE_TYPE, num);
        }
        if (contentList.size() == 0) {
            presenter.getList(Types.HOT_TYPE, num);
        }
        listAdapter = new MyListAdapter(contentList,null,getActivity());
        canContentView.setAdapter(listAdapter);
        titleAdapter = new MyTitleAdapter(this.titles,titles,tv_city,getActivity());
        rv.setAdapter(titleAdapter);
    }


    @Override
    public void onLoadMore() {
        num -= 15;
        presenter.getList(Types.HOT_CONTNT_TYPE, num);
    }

    @Override
    public void onRefresh() {
        presenter.getList(Types.HOT_TYPE, num);
    }


    @Override
    public void getContentList(List<Content> list, int type) {
        Message msg = Message.obtain(handler, type);
        msg.obj = list;
        msg.sendToTarget();
    }

    @Override
    public void getTitleList(List<Title> list, int type) {
        Message msg = Message.obtain(handler, type);
        msg.obj = list;
        msg.sendToTarget();
    }

    @OnClick({R.id.iv_location, R.id.add,R.id.city})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_location:
            case R.id.city:
                Intent intent=new Intent(getActivity(), LocationActivity.class);
                intent.putExtra("city",cityName);
                startActivityForResult(intent,1);
                break;
            case R.id.add:

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);

    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if("city".equals(intent.getAction())){
               City city = (City) intent.getSerializableExtra("city");
                cityName = city.getCityname();
                tv_city.setTag(city.getCitycode());
                tv_city.setText(cityName);
            }
        }
    }
}
