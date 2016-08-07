package com.example.administrator.yicheng.main.minef.store;

import com.example.administrator.yicheng.base.IBaseModel;
import com.example.administrator.yicheng.base.IBasePresenter;
import com.example.administrator.yicheng.base.IBaseView;
import com.example.administrator.yicheng.bean.Collection;

import java.util.List;

/**
 * Created by Jensen on 2016/8/6.
 */
public class StoreContract {
    public interface View extends IBaseView {
        void getCollection(List<Collection> collectionList);
    }

    public interface Presenter extends IBasePresenter {
        void getCollection();
    }

    public interface Model extends IBaseModel {
        List<Collection> getCollection();
    }
}
