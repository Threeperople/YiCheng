package com.example.administrator.yicheng.main.minef.store;


import com.example.administrator.yicheng.bean.Collection;
import com.example.administrator.yicheng.utils.LiteOrmUtils;

import java.util.List;

/**
 * Created by Jensen on 2016/8/6.
 */
public class StoreModel implements StoreContract.Model {
    @Override
    public List<Collection> getCollection() {
        List<Collection> queryAll = LiteOrmUtils.getQueryAll(Collection.class);
        return queryAll;
    }
}
