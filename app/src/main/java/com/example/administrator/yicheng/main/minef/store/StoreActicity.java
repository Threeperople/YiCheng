package com.example.administrator.yicheng.main.minef.store;


import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.example.administrator.yicheng.bean.CityContent;
import com.example.administrator.yicheng.bean.Collection;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.main.Read.webcontent.WebActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import b.Android;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/3.
 */
public class StoreActicity extends BaseActivity implements StoreContract.View{

    @BindView(R.id.store_toolBarIcon)
    ImageView storeToolBarIcon;
    @BindView(R.id.store_listView)
    ListView storeListView;
    private StorePresenter presenter;
    private List<String> list;
    private ArrayAdapter<String> adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        storeListView.setEmptyView(findViewById(R.id.store_emptyView));
        StoreModel model=new StoreModel();
        presenter = new StorePresenter(model,this);
        presenter.getCollection();
    }

    @Override
    public void initData() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.store_toolBarIcon)
    public void onClick() {
        finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String title) {
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(title)){
                list.remove(i);
                adapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    public void getCollection(final List<Collection> collectionList) {
        list = new ArrayList<>();
       for(int i=0;i<collectionList.size();i++){
           list.add(collectionList.get(i).getTitle());
       }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        storeListView.setAdapter(adapter);
        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(StoreActicity.this,WebActivity.class);
                intent.putExtra("collection",collectionList.get(position));
                startActivity(intent);
            }
        });
    }
}
