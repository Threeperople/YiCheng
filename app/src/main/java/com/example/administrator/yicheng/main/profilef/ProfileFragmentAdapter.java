package com.example.administrator.yicheng.main.profilef;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.bean.Profile;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.http.Url;

public class ProfileFragmentAdapter extends BaseAdapter{

    private List<Profile> mList;
    private Context mContext;

    public ProfileFragmentAdapter(Context context,List<Profile> list){

        this.mList= list;
        this.mContext = context;
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
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView= inflater.inflate(R.layout.profile_item_list,null);

            holder = new ViewHolder();
            holder.iv_imgurl = (ImageView) convertView.findViewById(R.id.iv_imgurl);
            holder.iv_exphead = (SimpleDraweeView) convertView.findViewById(R.id.iv_exphead);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_city= (TextView) convertView.findViewById(R.id.tv_city);
            holder.tv_expname = (TextView) convertView.findViewById(R.id.tv_expname);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        Profile profile = mList.get(position);
        String title = profile.getTitle();
        String city = profile.getCity();
        String expname = profile.getExpname();
        holder.tv_title.setText(title);
        holder.tv_expname.setText(expname);
        holder.tv_city.setText(city);

        String imgurl = profile.getImgurl();
        String exphead = profile.getExphead();

       Glide.with(mContext).load(imgurl).placeholder(R.mipmap.logo).into(holder.iv_imgurl);

        holder.iv_exphead.setImageURI(Uri.parse(exphead));
        return convertView;
    }

    class ViewHolder{
        ImageView iv_imgurl,iv_exphead;
        TextView tv_title,tv_city,tv_expname;
    }
}
