package com.example.administrator.yicheng.main.Read.news;

import com.example.administrator.yicheng.bean.ContentBean;
import com.example.administrator.yicheng.retrofit.HttpHelper;

import java.util.HashMap;

import retrofit2.Callback;

/**
 * Created by Jensen on 2016/8/3.
 */
public class NewsModel implements NewsContract.Model{

    @Override
    public void getContentList(int type, HashMap<String, String> params, Callback<ContentBean> callback) {
        HttpHelper.newInstance().getService().getContentList(params).enqueue(callback);
    }
}
