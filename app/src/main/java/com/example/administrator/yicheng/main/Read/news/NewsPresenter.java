package com.example.administrator.yicheng.main.Read.news;

import android.util.Log;

import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.CityContentBean;
import com.example.administrator.yicheng.bean.ContentBean;
import com.example.administrator.yicheng.config.Types;
import com.example.administrator.yicheng.config.Urlconfig;
import com.example.administrator.yicheng.utils.LiteOrmUtils;

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

    @Override
    public void getCityContentList(final int type, String cids, int num, String code) {
           map.clear();
        switch (type){
            case Types.CITY_TYPE:
                map.put(Urlconfig.CIDS,cids);
                map.put(Urlconfig.MSGID,"41000");
                map.put(Urlconfig.UP,"0");
                map.put(Urlconfig.CODE,code);
               model.getCityContentList(type, map, new Callback<CityContentBean>() {
                   @Override
                   public void onResponse(Call<CityContentBean> call, Response<CityContentBean> response) {
                       activity.getCityContentList(response.body().getData(),type);
                       Log.i("TAG", "onResponse: "+response.body().getData().get(0).getMtime());
                   }

                   @Override
                   public void onFailure(Call<CityContentBean> call, Throwable t) {

                   }
               });
                break;
            case Types.CITY_CONTNT_TYPE:
                map.put(Urlconfig.CIDS,cids);
                map.put(Urlconfig.MSGID,num+"");
                map.put(Urlconfig.UP,"1");
                map.put(Urlconfig.CODE,code);
                model.getCityContentList(type, map, new Callback<CityContentBean>() {
                    @Override
                    public void onResponse(Call<CityContentBean> call, Response<CityContentBean> response) {
                        activity.getCityContentList(response.body().getData(),type);
                    }

                    @Override
                    public void onFailure(Call<CityContentBean> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
