package com.example.administrator.yicheng.main.Read;

import android.util.Log;

import com.example.administrator.yicheng.bean.ContentBean;
import com.example.administrator.yicheng.bean.TitleBean;
import com.example.administrator.yicheng.config.Types;
import com.example.administrator.yicheng.config.Urlconfig;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2016/8/1.
 */
public class ReadPresenter implements ReadContract.Presenter {
    private ReadFragment fragment;
    private  ReadModel model;
    private HashMap<String, String> map = new HashMap<>();

    public  ReadPresenter(ReadModel model,ReadFragment fragment){
        this.model=model;
        this.fragment=fragment;
    }


    @Override
    public void getList(final int type,int num) {
        map.clear();
        switch (type){
            case Types.TITLE_TYPE:
                map.put(Urlconfig.UUID,"139c93aa892deec40d821fad8565d0b7");
                map.put(Urlconfig.UID,"0");
                map.put(Urlconfig.VERSION,"2.4.0");
                model.getTitleList(type, map,new Callback<TitleBean>() {
                    @Override
                    public void onResponse(Call<TitleBean> call, retrofit2.Response<TitleBean> response) {
                                fragment.getTitleList(response.body().getData(),type);
                    }

                    @Override
                    public void onFailure(Call<TitleBean> call, Throwable t) {
                    }
                });
                break;
            case Types.HOT_TYPE:
                map.put(Urlconfig.TIDS,Urlconfig.HOT_TIDS);
                map.put(Urlconfig.MSGID,"41000");
                map.put(Urlconfig.UP,"0");
                model.getContentList(type, map, new Callback<ContentBean>() {
                    @Override
                    public void onResponse(Call<ContentBean> call, Response<ContentBean> response) {
                        fragment.getContentList(response.body().getData(),type);
                        Log.i("TAG", "onResponse: "+response.body().getData().get(0).getMtime());

                    }

                    @Override
                    public void onFailure(Call<ContentBean> call, Throwable t) {
                        Log.i("TAG", "1onFailure: "+t.toString());
                    }
                });
                break;
            case Types.HOT_CONTNT_TYPE:
                map.put(Urlconfig.TIDS,Urlconfig.HOT_TIDS);
                map.put(Urlconfig.MSGID,num+"");
                map.put(Urlconfig.UP,"1");
                model.getContentList(type, map, new Callback<ContentBean>() {
                    @Override
                    public void onResponse(Call<ContentBean> call, Response<ContentBean> response) {
                        fragment.getContentList(response.body().getData(),type);

                    }

                    @Override
                    public void onFailure(Call<ContentBean> call, Throwable t) {
                        Log.i("TAG", "1onFailure: "+t.toString());
                    }
                });
                break;

        }
    }
}
