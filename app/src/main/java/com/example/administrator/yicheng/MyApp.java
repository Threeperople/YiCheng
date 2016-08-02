package com.example.administrator.yicheng;

import android.app.Application;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;


/**
 * Created by Administrator on 2016/7/29.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        BmobSMS.initialize(this, "61b2c5688479ec02987b6f5b3a440fbd");
//        Bmob.initialize(this, "61b2c5688479ec02987b6f5b3a440fbd");
        BmobSMS.initialize(this, "ac66f212be27a6f0d9135f158f0d7533");
        Bmob.initialize(this, "ac66f212be27a6f0d9135f158f0d7533");
    }
}
