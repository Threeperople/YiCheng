package com.example.administrator.yicheng.main.Read.news;

import android.util.Log;

import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.ContentBean;
import com.example.administrator.yicheng.config.Types;
import com.example.administrator.yicheng.config.Urlconfig;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jensen on 2016/8/3.
 */
public class NewsPresenter implements NewsContract.Presenter{
    private  NewsModel model;
    private  NewsActivity activity;
    private HashMap<String, String> map = new HashMap<>();
    public  NewsPresenter(NewsModel model, BaseActivity activity){
        this.model=model;
        this.activity= (NewsActivity) activity;
    }


    @Override
    public void getList(final int type, String id, int num) {
        map.clear();
        switch (type){
            case Types.HOT_TYPE:
                map.put(Urlconfig.TIDS,id);
                map.put(Urlconfig.MSGID,"41000");
                map.put(Urlconfig.UP,"0");
                model.getContentList(type, map, new Callback<ContentBean>() {
                    @Override
                    public void onResponse(Call<ContentBean> call, Response<ContentBean> response) {
                        activity.getContentList(response.body().getData(),type);
                        Log.i("TAG", "onResponse: "+response.body().getData().get(0).getMtime());
                    }

                    @Override
                    public void onFailure(Call<ContentBean> call, Throwable t) {
                    }
                });
                break;
            case Types.HOT_CONTNT_TYPE:
                map.put(Urlconfig.TIDS,id);
                map.put(Urlconfig.MSGID,num+"");
                map.put(Urlconfig.UP,"1");
                model.getContentList(type, map, new Callback<ContentBean>() {
                    @Override
                    public void onResponse(Call<ContentBean> call, Response<ContentBean> response) {
                        activity.getContentList(response.body().getData(),type);
                    }

                    @Override
                    public void onFailure(Call<ContentBean> call, Throwable t) {
                    }
                });
                break;
        }
    }
}
