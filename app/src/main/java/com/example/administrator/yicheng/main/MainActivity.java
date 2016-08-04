package com.example.administrator.yicheng.main;


import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.administrator.yicheng.FragmentFactory;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.the_main_activity_RadioGroup)
    RadioGroup theMainActivityRadioGroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        getSupportFragmentManager().beginTransaction().replace(R.id.the_main_activity_LinearLayoutContainer,
                FragmentFactory.creatFragment(FragmentFactory.Blogday_fragment)).commit();


        theMainActivityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    //第一页
                    case R.id.main_RadioGroup_one_blog_day:
                        transaction.replace(R.id.the_main_activity_LinearLayoutContainer,
                                FragmentFactory.creatFragment(FragmentFactory.Blogday_fragment));
                        break;
                    //第二页
                    case R.id.main_RadioGroup_two_reading:
                        transaction.replace(R.id.the_main_activity_LinearLayoutContainer,
                                FragmentFactory.creatFragment(FragmentFactory.Read_fragment));
                        break;
                    //第三页
                    case R.id.main_RadioGroup_three_profile:
                        transaction.replace(R.id.the_main_activity_LinearLayoutContainer,
                                FragmentFactory.creatFragment(FragmentFactory.Profile_fragment));
                        break;
                    //第四页
                    case R.id.main_RadioGroup_four_mine:
                        transaction.replace(R.id.the_main_activity_LinearLayoutContainer,
                                FragmentFactory.creatFragment(FragmentFactory.Activity_fragment));
                        break;
                }
                transaction.commit();
            }
        });
    }


    @Override
    public void initData() {

    }

}
