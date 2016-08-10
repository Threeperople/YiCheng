package com.example.administrator.yicheng.main.profilef;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.Profile;
import com.example.administrator.yicheng.config.Urls;
import com.example.administrator.yicheng.main.profilef.profilewebcity.CityPlayActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;



public class ProfileFragment extends BaseFragment implements ProfileContract.View,CanRefreshLayout.OnRefreshListener {


    @BindView(R.id.iv_loading)
    ImageView ivLoading;
    @BindView(R.id.can_content_view)
    ListView canContentView;
    @BindView(R.id.canrefresh_pro)
    CanRefreshLayout canrefreshPro;




    private List<Profile> list;
    private ProfileContract.Model model;
    private ProfileContract.Presenter presenter;
    private Handler handler = new Handler();
    private ProfileFragmentAdapter adapter;

    //URl参数初始值
    private int CURPAGE = 1;

    public void url() {


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }


    @Override
    public void initView() {

        canrefreshPro.setOnRefreshListener(this);
        canrefreshPro.setStyle(1, 0);
        list = new ArrayList<>();
        list.clear();
//        if (list.size()==0){
//           Toast.makeText(getContext(),"null",Toast.LENGTH_SHORT).show();
//           lv_profile.setVisibility(View.GONE);
        AnimationDrawable background = (AnimationDrawable) ivLoading.getBackground();
        background.start();
        canContentView.setEmptyView(ivLoading);
//       }
//        else {
//           Toast.makeText(getContext(),"have",Toast.LENGTH_SHORT).show();
//           lv_profile.setVisibility(View.VISIBLE);
//           ivLoading.setVisibility(View.GONE);
//       }


        adapter = new ProfileFragmentAdapter(getContext(), list);
        canContentView.setAdapter(adapter);

        model = new ProfileModel();
        presenter = new ProfilePresenter(this, model);
        presenter.initData(getdata());

        canContentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profile profile = list.get(position);
                Intent intent = new Intent(getActivity(), CityPlayActivity.class);
                intent.putExtra("goUrl", profile.getGourl());
                startActivity(intent);
            }
        });


    }


    private HashMap<String, String> getdata() {
        HashMap<String, String> mHashMap = new HashMap<>();

        mHashMap.put(Urls.Key.CURPAGE, "1");
        mHashMap.put(Urls.Key.CURPAGE_SIZE, "10");
        return mHashMap;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccessGetProfile(final List<Profile> profile) {

        handler.post(new Runnable() {
            @Override
            public void run() {

                list.addAll(profile);

                for (Profile i : list) {

                    Log.i("GGG", "run: " + i.toString());

                }


                adapter.notifyDataSetChanged();
//                lv_profile.setVisibility(View.VISIBLE);
//                ivLoading.setVisibility(View.GONE);
                Log.i("TAG", "--->>list" + list);
            }
        });
    }

    @Override
    public void onFailGetProfile(String msg) {

        Toast.makeText(getContext(), "请检查您的网络", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onRefresh() {
      new Thread(){
          @Override
          public void run() {
              super.run();
              try {
                  Thread.sleep(3000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              canrefreshPro.post(new Runnable() {
                  @Override
                  public void run() {
                    canrefreshPro.refreshComplete();
                  }
              });
          }
      }.start();
    }
}
