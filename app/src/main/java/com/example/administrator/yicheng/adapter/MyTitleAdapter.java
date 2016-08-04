package com.example.administrator.yicheng.adapter;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.Title;
import com.example.administrator.yicheng.main.Read.news.NewsActivity;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;


/**
 * Created by Jensen on 2016/7/29.
 */
public class MyTitleAdapter extends RecyclerView.Adapter<MyTitleAdapter.MyViewHolder> {
    private List<Title> mList;
    private Context mContext;
    public  MyTitleAdapter(List<Title> list, Context context){
        this.mList=list;
        this.mContext=context;

   }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.items_title,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         Title title=mList.get(position);
         String name = title.getName();
         holder.tv.setText(name);
         holder.tv.setTag(title);
    //     Picasso.with(mContext).load(title.getPic()).into(holder.iv);
         holder.iv.setImageURI(Uri.parse(title.getHead()));



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       private SimpleDraweeView iv;
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv= (SimpleDraweeView) itemView.findViewById(R.id.iv_title);
            tv= (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
             Title title= (Title) tv.getTag();
            if(title!=null){
                Intent intent=new Intent(mContext,NewsActivity.class);
                intent.putExtra("title",title);
                mContext.startActivity(intent);
            }
        }
    }
}
