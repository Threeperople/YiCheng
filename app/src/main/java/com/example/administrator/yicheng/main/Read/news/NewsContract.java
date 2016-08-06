package com.example.administrator.yicheng.main.Read.news;

import com.example.administrator.yicheng.base.IBaseModel;
import com.example.administrator.yicheng.base.IBasePresenter;
import com.example.administrator.yicheng.base.IBaseView;
import com.example.administrator.yicheng.bean.CityContent;
import com.example.administrator.yicheng.bean.CityContentBean;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.bean.ContentBean;

import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by Jensen on 2016/8/3.
 */
public class NewsContract {
    public interface View extends IBaseView {
        void getContentList(List<Content> list, int type);
        void getCityContentList(List<CityContent> list,int type);
    }

    public interface Presenter extends IBasePresenter {
        void getList(int type,String id,int num);
        void getCityContentList(int type,String cids,int num,String code);
    }

    public interface Model extends IBaseModel {
        void getContentList(int type, HashMap<String,String> params, Callback<ContentBean> callback);
        void getCityContentList(int type,HashMap<String,String> params,Callback<CityContentBean> callback);
    }
}
