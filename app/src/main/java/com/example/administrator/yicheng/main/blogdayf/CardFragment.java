package com.example.administrator.yicheng.main.blogdayf;


import android.mtp.MtpConstants;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends BaseFragment implements View.OnTouchListener{


    @BindView(R.id.blogday_item_backImage)
    SimpleDraweeView blogdayItemBackImage;
    @BindView(R.id.blogday_item_time)
    TextView blogdayItemTime;
    @BindView(R.id.blogday_item_title)
    TextView blogdayItemTitle;
    public Boolean flag = false;
    public  CardFragment(){

    }


    public static CardFragment newInstance(BlogdaycontentItem item) {
        CardFragment fragment = new CardFragment();
        Bundle bdl = new Bundle();

        bdl.putSerializable("item", item);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_card;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        BlogdaycontentItem mItem = (BlogdaycontentItem) arguments.getSerializable("item");
        blogdayItemTitle.setText( mItem.getTitle());
        String day = mItem.getDay().substring(6, 8);
        blogdayItemTime.setText(day);
        blogdayItemBackImage.setImageURI(mItem.getImages());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView() {

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float xdown=0;
        float ydown=0;
        float xup=0;
        float yup = 0;
        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            Log.i("TAG", "BlogdayFragment.onTouch."+"action_down");
            xdown= event.getX();
            ydown=event.getY();
        }
        if (event.getAction()==MotionEvent.ACTION_UP) {
            Log.i("TAG", "BlogdayFragment.onTouch."+"action_up");
            xup= event.getX();
            yup=event.getY();
        }

        if(flag){
            Log.i("TAG", "BlogdayFragment.onTouch."+"flag-----");
            if(yup>ydown){
                Log.i("TAG", "BlogdayFragment.onTouch."+"flag??");
//                blogdayOrientedViewPager.setVisibility(View.INVISIBLE);
            }
        }


        return false;
    }
}
