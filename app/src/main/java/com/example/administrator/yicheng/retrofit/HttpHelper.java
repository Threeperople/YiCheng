package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.config.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jensen on 2016/8/3.
 */
public class HttpHelper {
    private static HttpHelper helper;
    private Retrofit retrofit;
    IService service;

    private HttpHelper() {
        Gson gson = new GsonBuilder()
//                .enableComplexMapKeySerialization()
                .serializeNulls().create();
        retrofit = new Retrofit.Builder()
                //对提交的参数或者返回值进行预处理
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.BASEURL)
                .build();

    }

    public IService getService() {
        service = retrofit.create(IService.class);
        return service;
    }

    public static synchronized HttpHelper newInstance() {
        if (helper == null) {
            helper = new HttpHelper();
        }
        return helper;
    }
}
