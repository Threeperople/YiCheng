package com.example.administrator.yicheng.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.Content;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Jensen on 2016/7/29.
 */
public class MyListAdapter extends BaseAdapter {
    private List<Content> mList;


    public MyListAdapter(List<Content> list){
        mList=list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyListViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_hot,null);
        }
        viewHolder = getVieHolder(convertView);
        Content content = mList.get(position);
        viewHolder.tv_Content.setText(content.getTitle());
        viewHolder.tv_Editor.setText(content.getTags());
        viewHolder.tv_lookNum.setText(content.getJdata());
        Date date =new Date(Long.valueOf(content.getMtime())*1000);
        Date date1=new Date(System.currentTimeMillis());
        SimpleDateFormat sFormat = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat sFormat1 = new SimpleDateFormat("HH:mm");
        String s = sFormat.format(date);
        String s2= sFormat.format(date1);
        String time="";
        if(s.equals(s2)){
            time = sFormat1.format(date);
        }else{
            time=s;
        }
        viewHolder.tv_time.setText(time);
     //   Picasso.with(parent.getContext()).load(content.getPics()).into(viewHolder.iv_Content);
        viewHolder.iv_Content.setImageURI(Uri.parse(content.getPics()));
        return convertView;
    }

    public MyListViewHolder getVieHolder(View v) {
        MyListViewHolder viewHolder= (MyListViewHolder) v.getTag();
        if(viewHolder==null){
            viewHolder=new MyListViewHolder(v);
            v.setTag(viewHolder);
        }
        return viewHolder;
    }

    class MyListViewHolder{
        private ImageView iv_Content;
        private TextView tv_Content,tv_Editor,tv_time,tv_lookNum;

        public MyListViewHolder(View v) {
            this.iv_Content = (ImageView) v.findViewById(R.id.iv_Content);
            this.tv_Content = (TextView) v.findViewById(R.id.ContentTitle);
            this.tv_Editor = (TextView) v.findViewById(R.id.editor);
            this.tv_time = (TextView) v.findViewById(R.id.tv_time);
            this.tv_lookNum = (TextView) v.findViewById(R.id.lookNum);
        }
    }
}
