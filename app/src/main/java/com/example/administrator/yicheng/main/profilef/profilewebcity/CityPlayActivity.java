package com.example.administrator.yicheng.main.profilef.profilewebcity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.bean.Profile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityPlayActivity extends AppCompatActivity {

    @BindView(R.id.wv_cityPlay)
    WebView wvCityPlay;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.textview_comment_num)
    TextView textviewCommentNum;
    @BindView(R.id.relativie_city_paly)
    RelativeLayout relativieCityPaly;

    private Content content;
    private Profile profile;
    private String goUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city_play);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String goUrl = intent.getStringExtra("goUrl");
        WebSettings settings = wvCityPlay.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDisplayZoomControls(true);
        settings.setSupportZoom(true);
        wvCityPlay.loadUrl(String.valueOf(Uri.parse(goUrl)));
        wvCityPlay.setWebViewClient(new WebViewClient() {
            //重新加载url
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wvCityPlay.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
