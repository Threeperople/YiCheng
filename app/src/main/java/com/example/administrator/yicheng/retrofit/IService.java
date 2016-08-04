package com.example.administrator.yicheng.retrofit;



import com.example.administrator.yicheng.bean.ContentBean;
import com.example.administrator.yicheng.bean.TitleBean;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


/**
 * Created by Jensen on 2016/8/3.
 */
public interface IService {
    @GET("channel/orderlist")
    Call<TitleBean> getTitleList(@QueryMap()HashMap<String,String> params);
    @GET("content/ztMsgMore")
    Call<ContentBean> getContentList(@QueryMap()HashMap<String,String> params);
}
