package com.example.administrator.yicheng.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.CityContent;
import com.example.administrator.yicheng.bean.Content;
import com.example.administrator.yicheng.main.Read.webcontent.WebActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Jensen on 2016/7/29.
 */
public class MyListAdapter extends BaseAdapter {
    private List<Content> mList;
    private Context mContext;
    private List<CityContent> cityContentList;

    public MyListAdapter(List<Content> list, List<CityContent> cityList,Context context){
        mList=list;
        mContext=context;
        this.cityContentList=cityList;
    }
    @Override
    public int getCount() {
        if(mList.size()!=0) {
            return mList.size();
        }else{
            if(cityContentList!=null) {
                return cityContentList.size();
            }
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mList.size()!=0) {
            return mList.get(position);
        }else{
            if(cityContentList!=null) {
                return cityContentList.get(position);
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyListViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_hot,parent,false);
        }
        viewHolder = getVieHolder(convertView);
        if(mList.size()>0) {
            Content content = mList.get(position);
            viewHolder.tv_Content.setText(content.getTitle());
            viewHolder.tv_Editor.setText(content.getTags());
            String substring = content.getMtime().substring(content.getMtime().length() - 3);
            if ("000".equals(String.valueOf(substring))) {
                substring = content.getMtime().substring(content.getMtime().length() - 4);
            }
            viewHolder.tv_lookNum.setText(substring);
            Date date = new Date(Long.valueOf(content.getMtime()) * 1000);
            Date date1 = new Date(System.currentTimeMillis());
            SimpleDateFormat sFormat = new SimpleDateFormat("MM月dd日");
            SimpleDateFormat sFormat1 = new SimpleDateFormat("HH:mm");
            String s = sFormat.format(date);
            String s2 = sFormat.format(date1);
            String time = "";
            if (s.equals(s2)) {
                time = sFormat1.format(date);
            } else {
                time = s;
            }
            viewHolder.tv_time.setText(time);
            //   Picasso.with(parent.getContext()).load(content.getPics()).into(viewHolder.iv_Content);
            viewHolder.iv_Content.setImageURI(Uri.parse(content.getPics()));
            viewHolder.iv_Content.setTag(content);
        }else{
            CityContent cityContent = cityContentList.get(position);
            viewHolder.tv_Content.setText(cityContent.getTitle());
            viewHolder.tv_Editor.setText(cityContent.getTags());
            String substring = cityContent.getMtime().substring(cityContent.getMtime().length() - 3);
            if ("000".equals(String.valueOf(substring))) {
                substring = cityContent.getMtime().substring(cityContent.getMtime().length() - 4);
            }
            viewHolder.tv_lookNum.setText(substring);
            Date date = new Date(Long.valueOf(cityContent.getMtime()) * 1000);
            Date date1 = new Date(System.currentTimeMillis());
            SimpleDateFormat sFormat = new SimpleDateFormat("MM月dd日");
            SimpleDateFormat sFormat1 = new SimpleDateFormat("HH:mm");
            String s = sFormat.format(date);
            String s2 = sFormat.format(date1);
            String time = "";
            if (s.equals(s2)) {
                time = sFormat1.format(date);
            } else {
                time = s;
            }
            viewHolder.tv_time.setText(time);
            //   Picasso.with(parent.getContext()).load(content.getPics()).into(viewHolder.iv_Content);
            viewHolder.iv_Content.setImageURI(Uri.parse(cityContent.getImages()));
            viewHolder.iv_Content.setTag(cityContent);
        }
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

    class MyListViewHolder implements View.OnClickListener{
        private ImageView iv_Content;
        private TextView tv_Content,tv_Editor,tv_time,tv_lookNum;

        public MyListViewHolder(View v) {
            this.iv_Content = (ImageView) v.findViewById(R.id.iv_Content);
            this.tv_Content = (TextView) v.findViewById(R.id.ContentTitle);
            this.tv_Editor = (TextView) v.findViewById(R.id.editor);
            this.tv_time = (TextView) v.findViewById(R.id.tv_time);
            this.tv_lookNum = (TextView) v.findViewById(R.id.lookNum);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mList.size()>0) {
                Content content = (Content) iv_Content.getTag();
                if (content != null) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("contenturl", content);
                    mContext.startActivity(intent);
                }
            }else{
                CityContent cityContent= (CityContent) iv_Content.getTag();
                if(cityContent!=null){
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("cityContenturl", cityContent);
                    mContext.startActivity(intent);
                }
            }
        }
    }
}
