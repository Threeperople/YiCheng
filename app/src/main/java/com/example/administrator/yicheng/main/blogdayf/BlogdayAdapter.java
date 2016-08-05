package com.example.administrator.yicheng.main.blogdayf;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BlogdayAdapter extends BaseQuickAdapter<BlogdaycontentItem> {


    public BlogdayAdapter(int layoutResId, List<BlogdaycontentItem> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BlogdaycontentItem blogdaycontentItem) {
        baseViewHolder.setText(R.id.blogday_item_title,blogdaycontentItem.getTitle());

        String day = blogdaycontentItem.getDay().substring(6, 8);
        baseViewHolder.setText(R.id.blogday_item_time,day);

        SimpleDraweeView imageBack=(SimpleDraweeView)baseViewHolder.getView(R.id.blogday_item_backImage);
        imageBack.setImageURI(blogdaycontentItem.getImages());
    }


}
