package com.example.administrator.yicheng.main.blogdayf;

import com.example.administrator.yicheng.bean.BlogdayContentmain;
import com.example.administrator.yicheng.utils.HttpUtils;

import java.util.HashMap;

/**
 * Created by Jensen on 2016/8/1.
 */
public class BlogdayPresenter implements BlogdayContract.Presenter {
    private BlogdayContract.View view;
    private BlogdayContract.Model model;

    public BlogdayPresenter(BlogdayContract.Model model,BlogdayContract.View view){
        this.view = view;
        this.model = model;
    }


    @Override
    public void initData(String params) {
        model.getBlogdaymain(params, new HttpUtils.HttpCallBack<BlogdayContentmain>() {
            @Override
            public void onSucess(BlogdayContentmain blogdayContentmain) {
                view.onSuccessGetblogdayItem(blogdayContentmain.getData());
            }

            @Override
            public void onFail() {
                view.onFailGet("网络连接错误");
            }
        });
    }
}
