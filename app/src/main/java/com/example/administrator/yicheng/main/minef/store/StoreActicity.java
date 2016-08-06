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
    private SparseArray<Object> array;

    @Override
    public int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    public void initView() {
        storeListView.setEmptyView(findViewById(R.id.store_emptyView));
        StoreModel model=new StoreModel();
        presenter = new StorePresenter(model,this);
    }

    @Override
    public void initData() {

    }




    @OnClick(R.id.store_toolBarIcon)
    public void onClick() {
        finish();
    }

    @Override
    public void getCollection(List<Collection> collectionList) {
        List<String> titles=new ArrayList<>();
        array = new SparseArray();
        for(int i=0;i<collectionList.size();i++){
            BlogdaycontentItem blogdaycontentItem = collectionList.get(i).getBlogdaycontentItem();
            if(blogdaycontentItem!=null){
                titles.add(blogdaycontentItem.getTitle());
                array.put(i,blogdaycontentItem);
            }else{
                CityContent cityContent = collectionList.get(i).getCityContent();
                if(cityContent!=null){
                    titles.add(cityContent.getTitle());
                    array.put(i,cityContent);
                }else{
                    Content content = collectionList.get(i).getContent();
                    if(content!=null){
                        titles.add(content.getTitle());
                        array.put(i,content);
                    }
                }
            }
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
        storeListView.setAdapter(adapter);
        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(StoreActicity.this, WebActivity.class);
                Object o = array.get(position);
                Content content = (Content) o;
                if(content!=null){
                    intent.putExtra("contenturl",content);
                }else{
                    CityContent cityContent=(CityContent)o;
                    if(cityContent!=null){
                        intent.putExtra("cityContenturl",cityContent);
                    }else{
                        BlogdaycontentItem blogdaycontentItem= (BlogdaycontentItem) o;
                        if(blogdaycontentItem!=null){
                            intent.putExtra("url",blogdaycontentItem);
                        }
                    }
                }
               startActivity(intent);
            }
        });
    }
}
