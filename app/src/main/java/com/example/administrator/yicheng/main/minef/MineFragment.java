package com.example.administrator.yicheng.main.minef;


import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseFragment;
import com.example.administrator.yicheng.bean.RegisterPeople;
import com.example.administrator.yicheng.config.Flags;
import com.example.administrator.yicheng.main.minef.login.LogInActivity;
import com.example.administrator.yicheng.main.minef.login.setting.SettingActivity;
import com.example.administrator.yicheng.main.minef.msg.MsgActivity;
import com.example.administrator.yicheng.main.minef.settingAPP.SettingAppActivity;
import com.example.administrator.yicheng.main.minef.store.StoreActicity;
import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment implements MineContract.View, View.OnClickListener {


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
    @BindView(R.id.fourFragment_Login)
    CircleImageView fourFragmentLogin;
    private MyReceiver receiver;
    private MineModel mineModel;
    private MinePresenter minePresenter;
    private String number;
    private String name;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        Boolean f = (Boolean) SharedPreferenceUtils.get(getActivity(), Flags.IsLogInFlag, false);
        if(f) {
            getPerson();
        }
    }

    @Override
    public void initView() {
        Boolean f = (Boolean) SharedPreferenceUtils.get(getActivity(), Flags.IsLogInFlag, false);
        if(f) {
            getPerson();
        }
        fourFragmentLogin.setOnClickListener(this);
        fourFragmentTopName.setOnClickListener(this);
        fourFragmentMiddleMsgItem.setOnClickListener(this);
        fourFragmentMiddleStoreItem.setOnClickListener(this);
        fourFragmentMiddleSettingItem.setOnClickListener(this);
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("success");
        filter.addAction("exitLogIn");
        getActivity().registerReceiver(receiver, filter);

    }

    private void getPerson() {
        name = (String) SharedPreferenceUtils.get(getContext(), Flags.IsLogIngNameFlag, "");
        if (!TextUtils.isEmpty(name)) {
            List<RegisterPeople> peoples = LiteOrmUtils.getQueryByWhere(RegisterPeople.class, "number", new String[]{name});
            if (peoples .size()>0) {
                String userName = peoples.get(0).getUserName();
                fourFragmentTopName.setText(userName);
                fourFragmentTopName.setTextSize(16);
                String sex = peoples.get(0).getSex();
                fourFragmentEtSex.setText(sex);
                fourFragmentEtSex.setVisibility(View.VISIBLE);
                String uri = peoples.get(0).getUri();
                if (uri != null) {
                    ContentResolver cr = getActivity().getContentResolver();
                    try {
                        fourFragmentLogin.setImageBitmap(BitmapFactory.decodeStream(cr.openInputStream(Uri.fromFile(new File(uri)))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else{
                    fourFragmentLogin.setImageResource(R.mipmap.icon_default_avatar);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fourFragment_Login://点击转到登录界面，界面登录完成,改变登录内容
                String name = fourFragmentTopName.getText().toString().trim();
                if ("未登录".equals(name)) {//未登录时操作
                    Intent intent = new Intent(getActivity(), LogInActivity.class);
                    getActivity().startActivity(intent);
//                    activity左出右进动画效果
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

                } else {//登录后的操作，设置界面
                    Intent intent = new Intent(getActivity(), SettingActivity.class);
                    intent.putExtra("number", this.name);
                    getActivity().startActivity(intent);
                    //activity左出右进动画效果
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
                break;

            case R.id.fourFragment_middle_msgItem:

                Intent intent = new Intent(getActivity(), MsgActivity.class);
                getActivity().startActivity(intent);
                //activity左出右进动画效果
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);


                break;

            case R.id.fourFragment_middle_storeItem:

                getActivity().startActivity(new Intent(getActivity(), StoreActicity.class));
                //activity左出右进动画效果
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;

            case R.id.fourFragment_middle_settingItem:
                getActivity().startActivity(new Intent(getActivity(), SettingAppActivity.class));
                //activity左出右进动画效果
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
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
            if ("success".equals(intent.getAction())) {
                getIntentForLogIn(intent);//登录成功后，获得登录信息
            } else {
                getIntentToExit(intent);//点击MineFragment中的退出登录按钮，
            }


        }
    }

    private void getIntentToExit(Intent intent) {
        String action = intent.getAction();
        String exit = intent.getStringExtra("exit");
        if ("exitLogIn".equals(action)) {
            fourFragmentTopName.setText("未登录");
            fourFragmentTopName.setTextSize(16);
            fourFragmentEtSex.setVisibility(View.INVISIBLE);
            fourFragmentLogin.setImageResource(R.mipmap.icon_default_avatar);
            Boolean o = (Boolean) SharedPreferenceUtils.get(getContext(), Flags.IsLogInFlag, false);
            if (o) {
                SharedPreferenceUtils.remove(getContext(), Flags.IsLogInFlag);
                SharedPreferenceUtils.remove(getContext(),Flags.IsLogIngNameFlag);
            }
        }
    }

    private void getIntentForLogIn(Intent intent) {
        String action = intent.getAction();
        number = intent.getStringExtra("name");
        List<RegisterPeople> peoples = LiteOrmUtils.getQueryByWhere(RegisterPeople.class, "number", new String[]{number});
        String userName = peoples.get(0).getUserName();
        String sex = peoples.get(0).getSex();
        if ("success".equals(action)) {
            fourFragmentTopName.setText(userName);
            fourFragmentTopName.setTextSize(16);
            fourFragmentEtSex.setText(sex);
            fourFragmentEtSex.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
