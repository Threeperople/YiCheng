package com.example.administrator.yicheng.main.profilef;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.Profile;
import com.example.administrator.yicheng.config.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class ProfileFragment extends BaseFragment implements ProfileContract.View {


    @BindView(R.id.recycker)
    RecyclerView recycker;
    private List<Profile> list;
    private LinearLayoutManager layoutManager;
    private ProfileContract.Model  model;
    private ProfileContract.Presenter presenter ;
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
        adapter = new ProfileFragmentAdapter(R.layout.profile_item_list,list);
        layoutManager = new LinearLayoutManager(getContext());


        recycker.setLayoutManager(layoutManager);
        recycker.setAdapter(adapter);

        model = new ProfileModel();
        presenter = new ProfilePresenter(this, model);
        presenter.initData(getdata());
    }

    private HashMap<String, String> getdata() {
        HashMap<String,String> mHashMap= new HashMap<>();
        mHashMap.put(Urls.Key.CURPAGE,"1");
        mHashMap.put(Urls.Key.CURPAGE_SIZE,"10");
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
                for(Profile i:list){
                    Log.i("GGG", "run: "+i.toString());
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

}
