package com.example.administrator.yicheng.main.minef.store;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.Collection;

import com.example.administrator.yicheng.main.Read.webcontent.WebActivity;
import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;


import java.util.ArrayList;
import java.util.List;


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
        storeListView.setEmptyView(findViewById(R.id.empty_view));
        StoreModel model=new StoreModel();
        presenter = new StorePresenter(model,this);
        presenter.getCollection();
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.store_toolBarIcon)
    public void onClick() {
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getCollection();
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
        storeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(StoreActicity.this).setTitle("删除操作").
                        setMessage("是否狠心删除？").setPositiveButton("狠心删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = collectionList.get(position).getUrl();
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        LiteOrmUtils.deleteWhere(Collection.class,"url",new String[]{url});
                    }
                }).setNegativeButton("取消删除",null).show();
                return true;
            }
        });
    }
}
