package com.example.administrator.yicheng.main.blogdayf;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.example.administrator.yicheng.main.Read.webcontent.WebActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BlogdayaAdapterofmy extends RecyclerView.Adapter<BlogdayaAdapterofmy.MyViewHolder>{
    private Context mContext;
    private TextView tv;
    private  List<BlogdaycontentItem> mList;
    public BlogdayaAdapterofmy(Context context, List<BlogdaycontentItem> list,TextView tv){
        this.mContext=context;
        this.mList=list;
        this.tv=tv;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_blogday,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mList.get(position+4).getTitle());
        String day = mList.get(position + 4).getDay();
        if(day.length()>0) {
            String timeString = day.substring(6, 8);
            holder.time.setText(timeString);
            tv.setText("往期*"+day.substring(4,6)+"期");
        }
        holder.imageBack.setImageURI(Uri.parse(mList.get(position+4).getImages()));
//        Picasso.with(mContext).load(mList.get(position).getImages()).into(holder.imageBack);
    }

    @Override
    public int getItemCount() {
        return mList.size()-4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private SimpleDraweeView imageBack;
        private TextView title,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageBack =  (SimpleDraweeView) itemView.findViewById(R.id.blogday_item_backImage);
            title = (TextView) itemView.findViewById(R.id.blogday_item_title);
            time = (TextView) itemView.findViewById(R.id.blogday_item_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            BlogdaycontentItem item = mList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("url", item);
            mContext.startActivity(intent);
        }
    }
}
