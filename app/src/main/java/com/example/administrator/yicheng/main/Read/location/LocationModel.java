package com.example.administrator.yicheng.main.Read.location;

import com.example.administrator.yicheng.bean.CityBean;
import com.example.administrator.yicheng.retrofit.CityHelper;

import retrofit2.Callback;

/**
 * Created by Jensen on 2016/8/5.
 */
public class LocationModel implements LocationContract.Model {
    @Override
    public void getCityList(Callback<CityBean> callback) {
        CityHelper.newInstance().getService().getCityList().enqueue(callback);
    }
}
