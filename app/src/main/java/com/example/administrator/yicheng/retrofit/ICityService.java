package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.bean.CityBean;
import com.example.administrator.yicheng.bean.CityContent;
import com.example.administrator.yicheng.bean.CityContentBean;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


/**
 * Created by Jensen on 2016/8/5.
 */
public interface ICityService {
    @GET("channel/getcitydata")
    Call<CityBean> getCityList();
    @GET("content/ztMsgMore")
    Call<CityContentBean> getCityContentList(@QueryMap()HashMap<String,String> params);
}
