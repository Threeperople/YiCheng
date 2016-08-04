package com.example.administrator.yicheng.main.Read.webcontent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jensen on 2016/8/4.
 */
public class WebActivity extends BaseActivity {
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private static float y1;
    private static float y2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//           float rawY = event.getRawY();
//        Log.i("TAG", "onTouchEvent: "+event.getAction());
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                y1=rawY;
//                Log.i("TAG", "onTouchEvent: "+y1+"heh");
//                break;
//            case MotionEvent.ACTION_UP:
//                y2 = event.getRawY();
//                if(y2-y1>=10){
//                    Log.i("TAG", "onTouchEvent: 下"+y1+" "+y2);
//                    appbar.setVisibility(View.GONE);
//                }if(y1-y2>=10){
//                Log.i("TAG", "onTouchEvent: 上"+y1+" "+y2);
//                appbar.setVisibility(View.VISIBLE);
//            }
//                break;
//        }
//
//        return true;
//    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebSettings settings = web.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDisplayZoomControls(true);
        settings.setSupportZoom(true);
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            //重新加载url
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        web.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        y1=event.getRawY();
                        Log.i("TAG", "onTouchEvent: "+y1+"heh");
                        break;
                    case MotionEvent.ACTION_UP:
                        y2 = event.getRawY();
                        if(y2-y1>=10){
                            Log.i("TAG", "onTouchEvent: 下"+y1+" "+y2);
                            appbar.setVisibility(View.GONE);
                        }if(y1-y2>=10){
                        Log.i("TAG", "onTouchEvent: 上"+y1+" "+y2);
                        appbar.setVisibility(View.VISIBLE);
                    }
                        break;
                }
                return false;
            }
        });
    }


    @OnClick({R.id.iv_collection, R.id.iv_good, R.id.iv_talk, R.id.floating_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_collection:
                break;
            case R.id.iv_good:
                break;
            case R.id.iv_talk:
                break;
            case R.id.floating_button:
                finish();
                break;
        }
    }
}
