package com.example.administrator.yicheng;

import android.app.Application;

import cn.bmob.sms.BmobSMS;


/**
 * Created by Administrator on 2016/7/29.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BmobSMS.initialize(this, "ac66f212be27a6f0d9135f158f0d7533");
    }
}
