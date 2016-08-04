package com.example.administrator.yicheng.main.blogdayf;

import com.example.administrator.yicheng.base.IBaseModel;
import com.example.administrator.yicheng.base.IBasePresenter;
import com.example.administrator.yicheng.base.IBaseView;
import com.example.administrator.yicheng.bean.BlogdayContentmain;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.example.administrator.yicheng.utils.HttpUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jensen on 2016/8/1.
 */
public class BlogdayContract {
    public interface  View extends IBaseView{
        public void onSuccessGetblogdayItem(List<BlogdaycontentItem> blogdaycontentItems);
        public void onFailGet(String msg);
    }
    public interface  Model extends IBaseModel{
        public void getBlogdaymain(String params, HttpUtils.HttpCallBack<BlogdayContentmain> callBack);
    }
    public  interface  Presenter extends IBasePresenter{
        public void initData(String params);
    }
}
