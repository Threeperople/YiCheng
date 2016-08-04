package com.example.administrator.yicheng.main.blogdayf;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BlogdayaAdapterofmy extends RecyclerView.Adapter<BlogdayaAdapterofmy.MyViewHolder>{
    private Context mContext;
    private  List<BlogdaycontentItem> mList;
    public BlogdayaAdapterofmy(Context context, List<BlogdaycontentItem> list){
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_blogday,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());

        String timeString = mList.get(position).getDay().substring(6, 8);
        holder.time.setText(timeString);

        holder.imageBack.setImageURI(Uri.parse(mList.get(position).getImages()));
//        Picasso.with(mContext).load(mList.get(position).getImages()).into(holder.imageBack);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView imageBack;
        private TextView title,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageBack =  (SimpleDraweeView) itemView.findViewById(R.id.blogday_item_backImage);
            title = (TextView) itemView.findViewById(R.id.blogday_item_title);
            time = (TextView) itemView.findViewById(R.id.blogday_item_time);

        }
    }
}
