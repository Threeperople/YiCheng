package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.config.UrlsToBlogday;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/4.
 */
public class RetrofitHelper {
    private static RetrofitHelper instance;

    private RetrofitHelper() {
    }

    public static synchronized RetrofitHelper getInstance() {
        if (instance == null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }
    public Retrofit retrofit;
    public Gson gson;

    public <T> T createRetrofitService(Class<T> service) {//请求网络，并对数据造型
        if (retrofit == null) {
            createGson();
            initRetrofit();
        }
        return retrofit.create(service);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlsToBlogday.Path.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void createGson() {
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

}
