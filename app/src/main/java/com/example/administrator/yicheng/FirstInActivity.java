package com.example.administrator.yicheng;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yicheng.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FirstInActivity extends AppCompatActivity {

    private ViewPager my_viewpager;
    private List<ImageView> mList;
    private TextView tvTitle;
    private LinearLayout llPoints;
    private int prePosition = 0;
    private String[] titles;
    private Button button_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first_in);
        initView();
        initData();

        MyAdapter adapter = new MyAdapter();
        my_viewpager.setAdapter(adapter);
        llPoints.getChildAt(0).setBackgroundResource(R.mipmap.dot_enable);
        my_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(titles[position]);
                llPoints.getChildAt(position). setBackgroundResource(R.mipmap.dot_enable);
                llPoints.getChildAt(prePosition).setBackgroundResource(R.mipmap.dot_normal);
                prePosition = position;
                if(position==mList.size()-1){
                    button_to.setVisibility(View.VISIBLE);
                }else {
                    button_to.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        button_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstInActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        my_viewpager = (ViewPager) findViewById(R.id.my_viewpager);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        llPoints = (LinearLayout) findViewById(R.id.ll_points);
        button_to = (Button) findViewById(R.id.button_To);
    }

    private void initData() {
        int[] images = getImages();
        titles = getTitles();

        tvTitle.setText(titles[0]);

        mList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(images[i]);
            mList.add(iv);

            //添加小圆点
            View points = new View(this);
            points.setBackgroundResource(R.mipmap.dot_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            points.setLayoutParams(params);
            params.leftMargin = 5;
            llPoints.addView(points);
        }
    }
    public int[] getImages() {
        return new int[]{R.mipmap.welcome_icon_2
                ,R.mipmap.welcome_icon_3
                ,R.mipmap.welcome_beautiful
                ,R.mipmap.welcome_byhkuk
                ,R.mipmap.welcome_ohshakalaka};
    }

    public String[] getTitles() {

        return new String[]{"有我的世界！", "感受生活","超越自我","美女与野兽","一起来嗨皮！"};
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }
    }
}
