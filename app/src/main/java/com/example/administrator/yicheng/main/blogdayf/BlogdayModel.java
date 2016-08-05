package com.example.administrator.yicheng.main.blogdayf;

import com.example.administrator.yicheng.bean.BlogdayContentmain;
import com.example.administrator.yicheng.utils.HttpUtils;


/**
 * Created by Jensen on 2016/8/1.
 */
public class BlogdayModel implements BlogdayContract.Model {

    @Override
    public void getBlogdaymain(String params, HttpUtils.HttpCallBack<BlogdayContentmain> callBack) {

        HttpUtils.getInstance().getBlogdaymainData(params,callBack);

    }
}
