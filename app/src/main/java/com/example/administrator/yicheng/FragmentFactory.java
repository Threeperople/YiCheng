package com.example.administrator.yicheng;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.example.administrator.yicheng.minef.MineFragment;

/**
 * Created by Administrator on 2016/7/29.
 */
public class FragmentFactory {
    private static SparseArray<Fragment> fragments=new SparseArray<>();
    public static final int Blogday_fragment=0;
    public static final int Read_fragment= 1;
    public static final int Activity_fragment=2;
    public static final int Profile_fragment=3;


    public static  Fragment creatFragment(int position){
        Fragment fragment = fragments.get(position);
        if(fragment==null){
            switch (position) {
                case Blogday_fragment:
                    //这是“日荐”页面对应的fragment
                    fragment = new BlogdayFragment();
                    break;
                case Read_fragment:
                    //这是“发现”页面对应的fragment
                    fragment=new ReadFragment();
                    break;
                case Activity_fragment:
                    //这是“活动”页面对应的fragment
                    fragment=new MineFragment();
                    break;
                case Profile_fragment:
                    //这是“我”页面对应的fragment
                    fragment=new ProfileFragment();
                    break;
            }
            fragments.put(position,fragment);
        }
        return fragment;
    }
}
