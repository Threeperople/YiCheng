package com.example.administrator.yicheng.main.profilef;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.Profile;
import com.example.administrator.yicheng.config.Urls;
import com.example.administrator.yicheng.main.profilef.profilewebcity.CityPlayActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileFragment extends BaseFragment implements ProfileContract.View {


    @BindView(R.id.recycker)
    RecyclerView recycker;

    private List<Profile> list;
    private LinearLayoutManager layoutManager;
    private ProfileContract.Model model;
    private ProfileContract.Presenter presenter;
    private Handler handler = new Handler();
    private ProfileFragmentAdapter adapter;


    public ProfileFragment() {


    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }


    @Override
    public void initView() {

        list = new ArrayList<>();
        adapter = new ProfileFragmentAdapter(R.layout.profile_item_list, list);
        layoutManager = new LinearLayoutManager(getContext());


        recycker.setLayoutManager(layoutManager);
        recycker.setAdapter(adapter);

        model = new ProfileModel();
        presenter = new ProfilePresenter(this, model);
        presenter.initData(getdata());

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Profile profile = list.get(i);
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
                Log.i("TAG", "--->>list" + list);
            }
        });
    }

    @Override
    public void onFailGetProfile(String msg) {

        Toast.makeText(getContext(), "请检查您的网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
