package com.example.administrator.yicheng.main.Read.location;

import com.example.administrator.yicheng.base.IBaseModel;
import com.example.administrator.yicheng.base.IBasePresenter;
import com.example.administrator.yicheng.base.IBaseView;
import com.example.administrator.yicheng.bean.City;
import com.example.administrator.yicheng.bean.CityBean;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Jensen on 2016/8/5.
 */
public class LocationContract {
    public interface View extends IBaseView {
        void getCityList(List<City> list);
    }

    public interface Presenter extends IBasePresenter {
        void getList();
    }

    public interface Model extends IBaseModel {
        void getCityList(Callback<CityBean> callback);
    }
}
