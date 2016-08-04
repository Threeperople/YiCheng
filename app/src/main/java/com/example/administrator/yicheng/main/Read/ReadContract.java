package com.example.administrator.yicheng.main.Read;

import com.example.administrator.yicheng.base.IBaseModel;
import com.example.administrator.yicheng.base.IBasePresenter;
import com.example.administrator.yicheng.base.IBaseView;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.bean.ContentBean;
import com.example.administrator.yicheng.bean.Title;
import com.example.administrator.yicheng.bean.TitleBean;


import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;


/**
 * Created by Administrator on 2016/8/1.
 */
public class ReadContract {

    public interface View extends IBaseView{
        void getTitleList(List<Title> list, int type);
        void getContentList(List<Content> list, int type);
    }
    public interface Model extends IBaseModel{
        void getTitleList(int type, HashMap<String,String> params, Callback<TitleBean> callback);
        void getContentList(int type, HashMap<String,String> params, Callback<ContentBean> callback);
    }
    public interface Presenter extends IBasePresenter{
        void getList(int type,int num);
    }
}
