package com.example.administrator.yicheng.main.minef;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.main.minef.login.LogInActivity;
import com.example.administrator.yicheng.main.minef.login.setting.SettingActivity;
import com.example.administrator.yicheng.main.minef.msg.MsgActivity;
import com.example.administrator.yicheng.main.minef.settingAPP.SettingAppActivity;
import com.example.administrator.yicheng.main.minef.store.StoreActicity;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;

import butterknife.BindView;

public class MineFragment extends BaseFragment implements MineContract.View, View.OnClickListener {


    @BindView(R.id.fourFragment_Login)
    ImageView fourFragmentLogin;
    @BindView(R.id.fourFragment_top_name)
    TextView fourFragmentTopName;
    @BindView(R.id.fourFragment_et_sex)
    TextView fourFragmentEtSex;
    @BindView(R.id.fourFragment_middle_msgItem)
    LinearLayout fourFragmentMiddleMsgItem;
    @BindView(R.id.fourFragment_middle_storeItem)
    LinearLayout fourFragmentMiddleStoreItem;
    @BindView(R.id.fourFragment_middle_settingItem)
    LinearLayout fourFragmentMiddleSettingItem;
    private MyReceiver receiver;
    private MineModel mineModel;
    private MinePresenter minePresenter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        fourFragmentLogin.setOnClickListener(this);
        fourFragmentTopName.setOnClickListener(this);
        fourFragmentMiddleMsgItem.setOnClickListener(this);
        fourFragmentMiddleStoreItem.setOnClickListener(this);
        fourFragmentMiddleSettingItem.setOnClickListener(this);



        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("success");
        getActivity().registerReceiver(receiver,filter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fourFragment_Login://点击转到登录界面，界面登录完成,改变登录内容
                String name = fourFragmentTopName.getText().toString().trim();
                if("未登录".equals(name)){//未登录时操作
                    Intent intent = new Intent(getActivity(), LogInActivity.class);
                    getActivity().startActivity(intent);
//                    activity左出右进动画效果
                    getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);

                }else{//登录后的操作，设置界面

                    Intent intent = new Intent(getActivity(), SettingActivity.class);
                    getActivity().startActivity(intent);
                    //activity左出右进动画效果
                    getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                }
                break;

            case R.id.fourFragment_middle_msgItem:

                Intent intent = new Intent(getActivity(), MsgActivity.class);
                getActivity().startActivity(intent);
                //activity左出右进动画效果
                getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);


                break;

            case R.id.fourFragment_middle_storeItem:

                getActivity().startActivity(new Intent(getActivity(), StoreActicity.class));
                //activity左出右进动画效果
                getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;

            case R.id.fourFragment_middle_settingItem:
                getActivity().startActivity(new Intent(getActivity(), SettingAppActivity.class));
                //activity左出右进动画效果
                getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;
        }
    }

    @Override
    public void initData() {

    }


    /*
   * 使用广播接收器：
   *   1.当进入登录界面---》登录成功，修改名称和性别
   *   2.当进入设置界面----》修改name,sex,照片，都需要作更改；
   */
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type="";
            String action = intent.getAction();
            String name = intent.getStringExtra("name");

            String nameFromPhoneNum = (String) SharedPreferenceUtils.get(getActivity(), name, type);
            String  sex = (String) SharedPreferenceUtils.get(getActivity(), nameFromPhoneNum, type);


            if("success".equals(action)){
                fourFragmentTopName.setText(nameFromPhoneNum);
                fourFragmentTopName.setTextSize(16);

                fourFragmentEtSex.setText(sex);
                fourFragmentEtSex.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
