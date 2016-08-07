package com.example.administrator.yicheng.main.blogdayf;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.canyinghao.canrefresh.classic.RotateRefreshView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.example.administrator.yicheng.main.MainActivity;
import com.example.administrator.yicheng.main.Read.webcontent.WebActivity;
import com.example.administrator.yicheng.utils.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BlogdayFragment extends BaseFragment implements BlogdayContract.View, CanRefreshLayout.OnLoadMoreListener {


    @BindView(R.id.can_refresh_footer)
    RotateRefreshView canRefreshFooter;
    @BindView(R.id.can_content_view)
    RecyclerView canContentView;
    @BindView(R.id.blogday_item_month)
    TextView blogdayItemMonth;
    @BindView(R.id.canRefresh)
    CanRefreshLayout canRefresh;

    private List<BlogdaycontentItem> list;
    private BlogdayModel model;
    private BlogdayPresenter presenter;
    private BlogdayAdapter adapter;
    //    private BlogdayaAdapterofmy adapter;
    public int freshFlag = 1;
    public int changeYearFlag = 0;
    public int flag = 0;

    private Handler mhandle = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_blogday;
    }

    @Override
    public void initView() {

        list = new ArrayList<>();
        canRefresh.setOnLoadMoreListener(this);
  //      canRefresh.setRefreshEnabled(false);
        adapter = new BlogdayAdapter(R.layout.items_blogday, list);
//        adapter = new BlogdayaAdapterofmy(getContext(), list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        canContentView.setLayoutManager(gridLayoutManager);

        canContentView.setAdapter(adapter);

        model = new BlogdayModel();
        presenter = new BlogdayPresenter(model, this);

        presenter.initData(initParams());

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                BlogdaycontentItem item = list.get(i);
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url", item);
                startActivity(intent);
            }
        });
    }

    private String initParams() {
        String params = "";

        params = getDateString();
        return params;
    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccessGetblogdayItem(final List<BlogdaycontentItem> blogdaycontentItems) {
        mhandle.post(new Runnable() {
            @Override
            public void run() {
//                String month = blogdaycontentItems.get(0).getDay().substring(4, 6);
//                blogdayItemMonth.setText("往期*" + month + "月");

                list.addAll(blogdaycontentItems);
                adapter.notifyDataSetChanged();
                canRefresh.loadMoreComplete();
            }
        });

    }

    @Override
    public void onFailGet(String msg) {
        Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLoadMore() {
        freshFlag++;
        presenter.initData(initParams());
    }


    public String getDateString() {
        String nowtime = DateUtils.getNowtime();
        String[] split = nowtime.split("/");

        StringBuilder builder = new StringBuilder();

        if (builder.length() != 0) {//清空builder
            builder.delete(0, builder.length());
        }
        String item = "";
        for (int i = 29; i > 0; i--) {

            if (i > 10) {
                item = getYear(split[0], split[1]) + getMonth(split[1], freshFlag) + i + ",";
            } else {
                item = getYear(split[0], split[1]) + getMonth(split[1], freshFlag) + "0" + i + ",";
                if (i == 1) {
                    item = getYear(split[0], split[1]) + getMonth(split[1], freshFlag) + "0" + i;
                }
            }
            builder.append(item);
        }
        flag = 0;
        return builder.toString();
    }

    private String getYear(String year, String month) {//通过当前年份和刷新的次数的大小
        if (freshFlag == Integer.valueOf(month)) {
            if (flag == 0) {
                changeYearFlag++;
                flag++;
            }
        }

        return String.valueOf(Integer.valueOf(year) - changeYearFlag);
    }

    private String getMonth(String month, int changeMonth) {//通过当前月份和刷新的次数的大小

        if (Integer.valueOf(month) > changeMonth) {
            if (Integer.valueOf(month) - changeMonth > 10) {
                return String.valueOf(Integer.valueOf(month) - changeMonth);
            } else {
                return "0" + String.valueOf(Integer.valueOf(month) - changeMonth);
            }

        } else if (Integer.valueOf(month) < changeMonth) {
            Toast.makeText(getActivity(), "已到最后一页", Toast.LENGTH_SHORT).show();
            return null;

        } else if (Integer.valueOf(month) == changeMonth) {
            return "12";
        }
        return null;
    }

}
