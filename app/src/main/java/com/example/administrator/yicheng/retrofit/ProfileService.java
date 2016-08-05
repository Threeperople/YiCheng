package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.bean.ProfileBean;
import com.example.administrator.yicheng.config.Urls;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by My on 2016/8/5.
 */
public interface ProfileService {
    @GET(Urls.Path.ITEM_URL)
    Call<ProfileBean> getProfileBean(@QueryMap HashMap<String,String> params);
}
