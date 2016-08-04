package com.example.administrator.yicheng.utils;

import com.example.administrator.yicheng.bean.BlogdayContentmain;
import com.example.administrator.yicheng.retrofit.BlogDayService;
import com.example.administrator.yicheng.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/8/4.
 */
public class HttpUtils {
    private static HttpUtils instance;

    private HttpUtils() {
    }

    public static synchronized HttpUtils getInstance() {
        if (instance == null) {
            instance = new HttpUtils();
        }
        return instance;
    }
    public interface HttpCallBack<T>{
        public void onSucess(T t);
        public void onFail();

    }
    public void getBlogdaymainData(String params, final HttpCallBack<BlogdayContentmain> entityHttpCallBack) {
        Call<BlogdayContentmain> blogdayContentmainCall = RetrofitHelper
                .getInstance()
                .createRetrofitService(BlogDayService.class)//初始化retrofit
                .getBlogdaymain(params);//添加参数
        blogdayContentmainCall.enqueue(new Callback<BlogdayContentmain>() {
            @Override
            public void onResponse(Call<BlogdayContentmain> call, Response<BlogdayContentmain> response) {
                BlogdayContentmain blogdayContentmain = response.body();
                entityHttpCallBack.onSucess(response.body());
            }

            @Override
            public void onFailure(Call<BlogdayContentmain> call, Throwable t) {
                entityHttpCallBack.onFail();
            }
        });


    }


}
