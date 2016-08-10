package com.example.administrator.yicheng.main.blogdayf;



import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.canyinghao.canrefresh.classic.RotateRefreshView;
import com.canyinghao.canrefresh.google.GoogleCircleHookRefreshView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.BlogdaycontentItem;
import com.example.administrator.yicheng.main.Read.webcontent.WebActivity;
import com.example.administrator.yicheng.utils.DateUtils;
import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.example.administrator.yicheng.utils.NetWorkUtils;
import com.example.administrator.yicheng.utils.VerticalStackTransformer;
import com.example.administrator.yicheng.view.OrientedViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class BlogdayFragment extends BaseFragment
        implements BlogdayContract.View,
        CanRefreshLayout.OnLoadMoreListener
        ,CanRefreshLayout.OnRefreshListener{


    @BindView(R.id.can_refresh_footer)
    RotateRefreshView canRefreshFooter;
    @BindView(R.id.can_content_view)
    RecyclerView canContentView;
    @BindView(R.id.blogday_item_month)
    TextView blogdayItemMonth;
    @BindView(R.id.canRefresh)
    CanRefreshLayout canRefresh;
    @BindView(R.id.blogday_orientedViewPager)
    OrientedViewPager blogdayOrientedViewPager;
    @BindView(R.id.can_refresh_header)
    GoogleCircleHookRefreshView canRefreshHeader;

    private List<BlogdaycontentItem> list;
    private List<BlogdaycontentItem> theBigFourList;
    private BlogdayModel model;
    private BlogdayPresenter presenter;
  //  private BlogdayAdapter adapter;
    private BlogdayaAdapterofmy adapter;
    public int freshFlag = 1;
    public int changeYearFlag = 0;

    public int lastValue = -1;
    private List<Fragment> mFragments = new ArrayList<>();
    private Handler mhandle = new Handler();
    private ContentFragmentAdapter mContentFragmentAdapter;
     int lastDay ;
     int lastMonth ;
     int lastyear ;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_blogday;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        String nowtime = DateUtils.getNowtime();
        String[] split = nowtime.split("/");//时间数组
        int day = Integer.valueOf(split[2]);
        int month = Integer.valueOf(split[1]);
        int year = Integer.valueOf(split[0]);
        canRefresh.setOnLoadMoreListener(this);
        canRefresh.setOnRefreshListener(this);
        boolean b = NetWorkUtils.isNetworkAvailable(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        canContentView.setLayoutManager(gridLayoutManager);
        model = new BlogdayModel();
        presenter = new BlogdayPresenter(model, this);
        if(b){
            if(list.size()==0) {
                presenter.initData(initParams(day, month, year));
            }
        }else{
            if(list.size()==0){
                List<BlogdaycontentItem> all = LiteOrmUtils.getQueryAll(BlogdaycontentItem.class);
                list.addAll(all);
                initOrientedViewPager();
            }
        }
        //      canRefresh.setRefreshEnabled(false);
        adapter = new BlogdayaAdapterofmy(getContext(),list,blogdayItemMonth);
//        adapter = new BlogdayaAdapterofmy(getContext(), list);
        canContentView.setAdapter(adapter);

    }
    private void initOrientedViewPager() {
        for (int i = 0; i < 4; i++) {
            mFragments.add(CardFragment.newInstance(list.get(i)));
        }
        mFragments.add(new EmptyFragment());
        mContentFragmentAdapter = new
                ContentFragmentAdapter(getActivity().getSupportFragmentManager(), mFragments);
        //设置viewpager的方向为竖直
        blogdayOrientedViewPager.setOrientation(OrientedViewPager.Orientation.VERTICAL);
        //设置limit
        blogdayOrientedViewPager.setOffscreenPageLimit(4);
        //设置transformer
        blogdayOrientedViewPager.setPageTransformer(true
                , new VerticalStackTransformer(getActivity().getApplicationContext()));

        blogdayOrientedViewPager.setAdapter(mContentFragmentAdapter);
        blogdayOrientedViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==mFragments.size()-1){
                    blogdayOrientedViewPager.setVisibility(View.INVISIBLE);
                    blogdayOrientedViewPager.setCurrentItem(mFragments.size()-2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private String initParams(int day,int month,int year) {
        String params = "";
        params = getDateStringafter(day,month,year);
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
                LiteOrmUtils.deleteAll(BlogdaycontentItem.class);
                list.addAll(blogdaycontentItems);
                LiteOrmUtils.insertAll(list);
                if(list.size()==blogdaycontentItems.size()) {
                    initOrientedViewPager();
                }
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
        presenter.initData(initParams(lastDay,lastMonth,lastyear));
    }


    public String getDateStringafter(int day,int month,int year) {
        StringBuffer dataString = new StringBuffer();
        for (int i = 0; i <=31; i++) {
            if(i==31){
                lastDay = day;
                lastMonth = month;
                lastyear = year;
                break;
            }
            String itemString = getItemString(day, month, year);
            if (i == 30) {
                dataString.append(itemString);
            } else {
                dataString.append(itemString + ",");
            }
            if (day == 1) {

                if (month == 1) {
                    year--;
                    month = 12;
                } else {
                    month--;
                }
                day = theLastMothDays(month, year);
            } else {
                day--;
            }

        }
        return dataString.toString();
    }

    private String getItemString(int day, int month, int year) {
        StringBuffer item = new StringBuffer();
        item.append(String.valueOf(year));
        if (month < 10) {
            item.append("0" + month);
        } else {
            item.append(String.valueOf(month));
        }
        if (day < 10) {
            item.append("0" + day);
        } else {
            item.append(String.valueOf(day));
        }
        return item.toString();
    }

    private int theLastMothDays(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;

            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
        }
        return 0;
    }



    @Override
    public void onRefresh() {
        blogdayOrientedViewPager.setVisibility(View.VISIBLE);
        canRefresh.refreshComplete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragments.clear();
    }
}
