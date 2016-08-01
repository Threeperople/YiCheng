package com.example.administrator.yicheng;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private LinearLayout fragmentContainer;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                    case R.id.main_RadioGroup_four_activity:
                        transaction.replace(R.id.the_main_activity_LinearLayoutContainer,
                                FragmentFactory.creatFragment(FragmentFactory.Activity_fragment));
                        break;
                }
                transaction.commit();
            }
        });


    }

    private void initView() {
        fragmentContainer = (LinearLayout) findViewById(R.id.the_main_activity_LinearLayoutContainer);
        mRadioGroup = (RadioGroup) findViewById(R.id.the_main_activity_RadioGroup);
    }
}
