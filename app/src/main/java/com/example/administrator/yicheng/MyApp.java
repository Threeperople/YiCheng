package com.example.administrator.yicheng;

import android.app.Application;

import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Created by Administrator on 2016/7/29.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
