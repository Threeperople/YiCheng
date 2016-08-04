package com.example.administrator.yicheng.main.Read;


import com.example.administrator.yicheng.bean.ContentBean;
import com.example.administrator.yicheng.bean.TitleBean;
import com.example.administrator.yicheng.retrofit.HttpHelper;

import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;


/**
 * Created by Administrator on 2016/8/1.
 */
public class ReadModel implements ReadContract.Model {


    @Override
    public void getTitleList(int type, HashMap<String, String> params, Callback<TitleBean> callback) {
        HttpHelper.newInstance().getService().getTitleList(params).enqueue(callback);
    }

    @Override
    public void getContentList(int type, HashMap<String, String> params, Callback<ContentBean> callback) {
        HttpHelper.newInstance().getService().getContentList(params).enqueue(callback);
    }
}

