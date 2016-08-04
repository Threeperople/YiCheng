package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.bean.BlogdayContentmain;
import com.example.administrator.yicheng.config.UrlsToBlogday;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/4.
 */
public interface BlogDayService {
    @GET(UrlsToBlogday.Path.ITEM_URL)
    Call<BlogdayContentmain> getBlogdaymain(@Query(UrlsToBlogday.Key.DAY_TIME) String value);

}