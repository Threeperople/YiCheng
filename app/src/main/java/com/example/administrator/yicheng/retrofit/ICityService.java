package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.bean.CityBean;


import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Jensen on 2016/8/5.
 */
public interface ICityService {
    @GET("channel/getcitydata")
    Call<CityBean> getCityList();
}
