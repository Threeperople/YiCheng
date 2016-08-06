package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.config.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jensen on 2016/8/5.
 */
public class CityHelper {
    private static CityHelper helper;
    private Retrofit retrofit;
    ICityService service;

    private CityHelper() {
        Gson gson = new GsonBuilder()
//                .enableComplexMapKeySerialization()
                .serializeNulls().create();
        retrofit = new Retrofit.Builder()
                //对提交的参数或者返回值进行预处理
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.CITYURL)
                .build();

    }

    public ICityService getService() {
        service = retrofit.create(ICityService.class);
        return service;
    }

    public static synchronized CityHelper newInstance() {
        if (helper == null) {
            helper = new CityHelper();
        }
        return helper;
    }
}

