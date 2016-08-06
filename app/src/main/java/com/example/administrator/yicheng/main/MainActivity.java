package com.example.administrator.yicheng.main;


import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.administrator.yicheng.FragmentFactory;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.main.Read.ReadFragment;
import com.example.administrator.yicheng.main.blogdayf.BlogdayFragment;
import com.example.administrator.yicheng.main.minef.MineFragment;
import com.example.administrator.yicheng.main.profilef.ProfileFragment;
import com.example.administrator.yicheng.utils.LiteOrmUtils;

import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.the_main_activity_RadioGroup)
    RadioGroup theMainActivityRadioGroup;
    private BlogdayFragment blogdayFragment;
    private ReadFragment readFragment;
    private ProfileFragment profileFragment;
    private MineFragment mineFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    public void initView() {
        LiteOrmUtils.creatLiteOrm(this,"registerPeople");//单例模式创建唯一数据库
        blogdayFragment = (BlogdayFragment) FragmentFactory.creatFragment(FragmentFactory.Blogday_fragment);
        readFragment = (ReadFragment) FragmentFactory.creatFragment(FragmentFactory.Read_fragment);
        profileFragment = (ProfileFragment) FragmentFactory.creatFragment(FragmentFactory.Profile_fragment);
        mineFragment = (MineFragment) FragmentFactory.creatFragment(FragmentFactory.Activity_fragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.the_main_activity_LinearLayoutContainer,
                blogdayFragment).add(R.id.the_main_activity_LinearLayoutContainer,
                readFragment).add(R.id.the_main_activity_LinearLayoutContainer,
                profileFragment).add(R.id.the_main_activity_LinearLayoutContainer,
                mineFragment);
               fragmentTransaction.hide(readFragment).hide(profileFragment).hide(mineFragment).commit();
        theMainActivityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    //第一页
                    case R.id.main_RadioGroup_one_blog_day:
                        fragmentTransaction.hide(readFragment).hide(profileFragment).hide(mineFragment).show(blogdayFragment);
                        break;
                    //第二
                    case R.id.main_RadioGroup_two_reading:
                        fragmentTransaction.hide(blogdayFragment).hide(profileFragment).hide(mineFragment).show(readFragment);
                        break;
                    //第三页
                    case R.id.main_RadioGroup_three_profile:
                        fragmentTransaction.hide(blogdayFragment).hide(readFragment).hide(mineFragment).show(profileFragment);
                        break;
                    //第四页
                    case R.id.main_RadioGroup_four_mine:
                        fragmentTransaction.hide(blogdayFragment).hide(profileFragment).hide(readFragment).show(mineFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public void initData() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
