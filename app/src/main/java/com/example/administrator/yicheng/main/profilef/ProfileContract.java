package com.example.administrator.yicheng.main.profilef;

import com.example.administrator.yicheng.base.IBaseModel;
import com.example.administrator.yicheng.base.IBasePresenter;
import com.example.administrator.yicheng.base.IBaseView;
import com.example.administrator.yicheng.bean.Profile;
import com.example.administrator.yicheng.bean.ProfileBean;
import com.example.administrator.yicheng.utils.HttpUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jensen on 2016/8/1.
 */
public class ProfileContract {

    public interface  View extends IBaseView{

        public void onSuccessGetProfile(List<Profile> profileBeans);

        public void onFailGetProfile(String msg);

    }
    public  interface  Model extends IBaseModel{
        public void getProfile(HashMap<String, String> params, HttpUtils.HttpCallBack<ProfileBean> callBack);


    }
    public  interface  Presenter extends IBasePresenter{
        public void initData(HashMap<String, String> params);
    }
}
