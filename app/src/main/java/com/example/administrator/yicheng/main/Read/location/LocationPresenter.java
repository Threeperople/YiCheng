package com.example.administrator.yicheng.main.Read.location;

import com.example.administrator.yicheng.bean.City;
import com.example.administrator.yicheng.bean.CityBean;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jensen on 2016/8/5.
 */
public class LocationPresenter implements LocationContract.Presenter {
    private LocationContract.View mView;
    private LocationModel model;
    public  LocationPresenter(LocationModel model, LocationContract.View view){
         mView=view;
         this.model=model;
    }
    @Override
    public void getList() {
       model.getCityList(new Callback<CityBean>() {
           @Override
           public void onResponse(Call<CityBean> call, Response<CityBean> response) {
               List<City> cities = response.body().getData();
              Collections.sort(cities);
               mView.getCityList(cities);
           }

           @Override
           public void onFailure(Call<CityBean> call, Throwable t) {

           }
       });
    }


}
