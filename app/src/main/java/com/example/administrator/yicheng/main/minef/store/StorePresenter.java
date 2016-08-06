package com.example.administrator.yicheng.main.minef.store;


import com.example.administrator.yicheng.bean.Collection;

import java.util.List;

/**
 * Created by Jensen on 2016/8/6.
 */
public class StorePresenter implements StoreContract.Presenter {
    private StoreModel model;
    private StoreActicity acticity;
    public StorePresenter (StoreModel model,StoreActicity acticity){
        this.model=model;
        this.acticity=acticity;
    }

    @Override
    public void getCollection() {
        List<Collection> collection = model.getCollection();
        acticity.getCollection(collection);
    }
}
