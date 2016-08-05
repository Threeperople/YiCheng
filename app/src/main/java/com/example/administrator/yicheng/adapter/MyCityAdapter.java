package com.example.administrator.yicheng.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.City;
import com.example.administrator.yicheng.main.MainActivity;
import com.example.administrator.yicheng.main.Read.location.LocationActivity;

import java.util.List;

/**
 * Created by Jensen on 2016/8/5.
 */
public class MyCityAdapter extends BaseAdapter {
    private List<City> mLsit;
    private Context mContext;
    private String s="";

    public MyCityAdapter(List<City> list,Context context){
        mLsit=list;
        mContext= context;

    }
    @Override
    public int getCount() {
        return mLsit.size();
    }

    @Override
    public Object getItem(int position) {
        return mLsit.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.items_city,null);
        }
        holder=getHolder(convertView);
        City city=mLsit.get(position);
        holder.tv_city.setText(city.getCityname());
        holder.tv_pinyin.setText(city.getEname());
        String str = city.getEname().substring(0, 1);
        if(!s.equals(str)) {
            holder.tv_first.setText(str);
            holder.tv_first.setVisibility(View.VISIBLE);
            s=str;
        }else{
            holder.tv_first.setVisibility(View.GONE);
        }
        return convertView;
    }

    public MyViewHolder getHolder(View v) {
        MyViewHolder holder= (MyViewHolder) v.getTag();
        if(holder==null){
            holder=new MyViewHolder(v);
            v.setTag(holder);
        }
        return holder;
    }

    class MyViewHolder {
       private TextView tv_city,tv_pinyin,tv_first;
        public MyViewHolder(View itemView) {
            tv_city= (TextView) itemView.findViewById(R.id.city_name);
            tv_pinyin= (TextView) itemView.findViewById(R.id.city_pinyin);
            tv_first= (TextView) itemView.findViewById(R.id.tv_first);

        }

    }
}
