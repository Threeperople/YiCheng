package com.example.administrator.yicheng.main.Read.news;

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
                map.put(Urlconfig.TIDS,Urlconfig.HOT_TIDS);
                map.put(Urlconfig.MSGID,"41000");
                map.put(Urlconfig.UP,"0");
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
