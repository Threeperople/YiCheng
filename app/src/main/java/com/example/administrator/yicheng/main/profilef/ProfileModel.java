package com.example.administrator.yicheng.main.profilef;

import com.example.administrator.yicheng.bean.ProfileBean;
import com.example.administrator.yicheng.utils.HttpUtils;

import java.util.HashMap;

/**
 * Created by Jensen on 2016/8/1.
 */
public class ProfileModel implements ProfileContract.Model{

    @Override
    public void getProfile(HashMap<String, String> params, HttpUtils.HttpCallBack<ProfileBean> callBack) {
        HttpUtils.getInstance().getProfileBeanData(params, callBack);
    }
}
