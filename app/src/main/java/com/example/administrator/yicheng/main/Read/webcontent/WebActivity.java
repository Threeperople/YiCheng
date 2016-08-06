package com.example.administrator.yicheng.main.Read.webcontent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.example.administrator.yicheng.bean.CityContent;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.main.MainActivity;
import com.example.administrator.yicheng.main.minef.login.LogInActivity;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.iv_collection)
    ImageView ivCollection;
    @BindView(R.id.iv_good)
    ImageView ivGood;
    @BindView(R.id.iv_talk)
    ImageView ivTalk;
    @BindView(R.id.floating_button)
    FloatingActionButton floatingButton;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    private String url;
    private static boolean b;
    private Content content;
    private BlogdaycontentItem blogdaycontentItem;

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
        content = (Content) intent.getSerializableExtra("contenturl");
        if(content==null){
            blogdaycontentItem = (BlogdaycontentItem) intent.getSerializableExtra("url");
            if(blogdaycontentItem==null){
                CityContent cityContent= (CityContent) intent.getSerializableExtra("cityContenturl");
                url=cityContent.getSummary();
            }else {
                url = blogdaycontentItem.getUrl();
            }
        }else {
            url = content.getSummary();
        }
        b = (Boolean) SharedPreferenceUtils.get(this,url, false);
        if (b) {
            ivGood.setImageResource(R.mipmap.icon_like_green);
        }
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
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y1 = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        y2 = event.getRawY();
                        if (y2 - y1 >= 10) {
                            appbar.setVisibility(View.GONE);
                        }
                        if (y1 - y2 >= 10) {
                            appbar.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                return false;
            }
        });
    }


    @OnClick({R.id.iv_collection, R.id.iv_good, R.id.iv_talk, R.id.floating_button,R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_collection:

                startActivity(new Intent(this, LogInActivity.class));
                break;
            case R.id.iv_good:
                if (b) {
                    ivGood.setImageResource(R.mipmap.icon_like);
                    b = false;
                    SharedPreferenceUtils.remove(this, url);
                } else {
                    SharedPreferenceUtils.putAndApply(this, url, true);
                    b = true;
                    ivGood.setImageResource(R.mipmap.icon_like_green);
                }
                break;
            case R.id.iv_share:
                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                        {
                                 SHARE_MEDIA.SINA,SHARE_MEDIA.RENREN,
                                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                        };
                UMShareListener listener = new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Toast.makeText(WebActivity.this, "分享成功了", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Toast.makeText(WebActivity.this, "分享失败了", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                };
                if(content!=null) {
                    new ShareAction(this).setDisplayList(displaylist)
                            .withText(content.getContent())
                            .withTitle(content.getTitle())
                            .withTargetUrl(url)
                            .setDisplayList(displaylist)
                            .setListenerList(listener)
                            .open();
                }else{
                    new ShareAction(this).setDisplayList(displaylist)
                            .withText(blogdaycontentItem.getTitle())
                            .withTitle(blogdaycontentItem.getAuthor())
                            .withTargetUrl(url)
                            .setDisplayList(displaylist)
                            .setListenerList(listener)
                            .open();
                }

                break;
            case R.id.iv_talk:
                break;
            case R.id.floating_button:
                finish();
                break;
        }
    }

}
