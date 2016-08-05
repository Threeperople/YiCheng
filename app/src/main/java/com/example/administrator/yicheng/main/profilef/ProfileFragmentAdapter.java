package com.example.administrator.yicheng.main.profilef;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.Profile;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

/**
 * Created by zhonghang on 16/8/3.
 */

public class ProfileFragmentAdapter extends BaseQuickAdapter<Profile> {
    public ProfileFragmentAdapter(int layoutResId, List<Profile> data) {
        super(layoutResId, data);
        Log.i("TAG","---data"+data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Profile profile) {
        baseViewHolder.setText(R.id.tv_title, profile.getTitle())
                .setText(R.id.tv_city, profile.getCity())
                .setText(R.id.tv_expname, profile.getExpname());
        Glide.with(baseViewHolder.convertView.getContext())
                .load(profile.getImgurl())
                .into((ImageView) baseViewHolder.getView(R.id.iv_imgurl));

        SimpleDraweeView view = baseViewHolder.getView(R.id.iv_exphead);
        view.setImageURI(profile.getExphead());

//        ImageOptions options = new ImageOptions.Builder()
//                .setCircular(true)//圆形图片
//                .setConfig(Bitmap.Config.ARGB_8888)
//                .setFailureDrawableId(R.mipmap.ic_launcher)
//                .setLoadingDrawableId(R.mipmap.ic_launcher)
//                .setIgnoreGif(true)//是否显示gif图片
////                .setSize(DensityUtil.px2dip(20), DensityUtil.px2dip(20))//设置图片尺寸
//                .build();
//        x.image().bind((ImageView)baseViewHolder.getView(R.id.iv_exphead), profile.getExphead(), options);


    }
}
