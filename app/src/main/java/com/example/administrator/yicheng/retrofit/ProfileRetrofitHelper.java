package com.example.administrator.yicheng.retrofit;

import com.example.administrator.yicheng.config.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileRetrofitHelper {
    private static ProfileRetrofitHelper instance;

    private ProfileRetrofitHelper() {
    }

    public static synchronized ProfileRetrofitHelper getInstance() {
        if (instance == null) {
            instance = new ProfileRetrofitHelper();
        }
        return instance;
    }

    public Retrofit retrofit;
    public Gson gson;

    public <T> T createRetrofitService(Class<T> service) {
        if (retrofit == null) {
            createGson();
            initRetrofit();
        }
        return retrofit.create(service);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.Path.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void createGson() {
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

}