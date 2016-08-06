package com.example.administrator.yicheng.main.minef.settingAPP.aboutus;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yicheng.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.aboutUs_verson)
    TextView aboutUsVerson;
    @BindView(R.id.abuoutUs_toolBarIcon)
    ImageView abuoutUsToolBarIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            aboutUsVerson.setText("V" + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.abuoutUs_toolBarIcon)
    public void onClick() {
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
