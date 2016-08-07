package com.example.administrator.yicheng.main.Read.webcontent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
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
import com.example.administrator.yicheng.bean.Collection;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.config.Flags;
import com.example.administrator.yicheng.main.MainActivity;
import com.example.administrator.yicheng.main.minef.login.LogInActivity;
import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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
    private CityContent cityContent;
    private Boolean f;
    private static boolean s=false;
    private String nr;
    private String title;
    private Collection collection;


    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {
        Intent intent = getIntent();
        content = (Content) intent.getSerializableExtra("contenturl");
        if(content==null){
            blogdaycontentItem = (BlogdaycontentItem) intent.getSerializableExtra("url");
            if(blogdaycontentItem==null){
                cityContent = (CityContent) intent.getSerializableExtra("cityContenturl");
                if(cityContent==null){
                    collection = (Collection) intent.getSerializableExtra("collection");
                    url=collection.getUrl();
                    nr=collection.getContent();
                    title=collection.getTitle();
                }else {
                    url = cityContent.getSummary();
                    nr = cityContent.getDescription();
                    title = cityContent.getTitle();
                }
            }else {
                url = blogdaycontentItem.getUrl();
                nr=blogdaycontentItem.getDescription();
                title=blogdaycontentItem.getTitle();
            }
        }else {
            url = content.getSummary();
            nr=content.getContent();
            title=content.getTitle();
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
        f = (Boolean) SharedPreferenceUtils.get(this, Flags.IsLogInFlag, false);
        if(!f){
            ivCollection.setImageResource(R.mipmap.icon_star);
        }else{
            List<Collection> collections= LiteOrmUtils.getQueryByWhere(Collection.class, "url", new String[]{this.url});
            if(collections.size()>0) {
                Collection collection = collections.get(0);
                if (TextUtils.equals(collection.getUrl(), url)) {
                    ivCollection.setImageResource(R.mipmap.icon_star_green);
                    s = true;
                } else {
                    ivCollection.setImageResource(R.mipmap.icon_star);
                    s = false;
                }
            }
        }
    }


    @OnClick({R.id.iv_collection, R.id.iv_good, R.id.iv_talk, R.id.floating_button,R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_collection:
                if(f) {
                    if(s){
                        ivCollection.setImageResource(R.mipmap.icon_star);
                        s=false;
                        LiteOrmUtils.deleteWhere(Collection.class,"url",new String[]{url});
                    }else {
                        ivCollection.setImageResource(R.mipmap.icon_star_green);
                        Collection collection=new Collection(url,title,nr);
                        LiteOrmUtils.insert(collection);
                        s=true;
                        EventBus.getDefault().post(title);
                    }
                }else {
                    startActivity(new Intent(this, LogInActivity.class));
                }
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
                new ShareAction(this).setDisplayList(displaylist)
                            .withText(nr)
                            .withTitle(title)
                            .withTargetUrl(url)
                            .setDisplayList(displaylist)
                            .setListenerList(listener)
                            .open();
                break;
            case R.id.iv_talk:
                break;
            case R.id.floating_button:
                finish();
                break;
        }
    }

}
