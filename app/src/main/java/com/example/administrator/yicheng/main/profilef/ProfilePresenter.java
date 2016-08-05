package com.example.administrator.yicheng.main.profilef;
import com.example.administrator.yicheng.bean.ProfileBean;
import com.example.administrator.yicheng.utils.HttpUtils;

import java.util.HashMap;

/**
 * Created by Jensen on 2016/8/1.
 */
public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ProfileContract.Model model;

    public ProfilePresenter(ProfileContract.View view, ProfileContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void initData(HashMap<String, String> params) {

        model.getProfile(params, new HttpUtils.HttpCallBack<ProfileBean>() {

            @Override
            public void onSucess(ProfileBean profileBean) {
                view.onSuccessGetProfile(profileBean.getData());

            }

            @Override
            public void onFail() {
                view.onFailGetProfile("网络连接错误");
            }
        });
    }
}
