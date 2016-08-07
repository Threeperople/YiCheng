package com.example.administrator.yicheng.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.Comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Jensen on 2016/8/7.
 */
public class CommentAdapter extends BaseAdapter {
    private List<Comment> mList;
    public CommentAdapter(List<Comment> commentList){
        mList=commentList;
    }
    @Override
    public int getCount() {
        return mList!=null?mList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mList!=null?mList.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,null);
        }
         holder = getHolder(convertView);
        Comment comment = mList.get(position);
        holder.tv_text.setText(comment.getWord());
        holder.tv_name.setText(comment.getName());
        SimpleDateFormat sFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        Date date=new Date(comment.getTime());
        String format = sFormat.format(date);
        holder.tv_time.setText(format);
        return convertView;
    }

    private ViewHolder getHolder(View convertView) {
        ViewHolder holder= (ViewHolder) convertView.getTag();
        if(holder==null){
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        return holder;
    }

    class ViewHolder{
        private TextView tv_name,tv_time,tv_text;

        public ViewHolder(View v) {
            tv_name= (TextView) v.findViewById(R.id.comment_name);
            tv_time= (TextView) v.findViewById(R.id.comment_time);
            tv_text= (TextView) v.findViewById(R.id.comment_text);
        }
    }
}
