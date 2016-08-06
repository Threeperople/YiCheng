package com.example.administrator.yicheng.main.minef.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.RegisterPeople;
import com.example.administrator.yicheng.config.Flags;
import com.example.administrator.yicheng.main.minef.login.register.RegisterActivity;
import com.example.administrator.yicheng.utils.LiteOrmUtils;
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Phaser;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/2.
 */
public class LogInActivity extends BaseActivity {
    @BindView(R.id.login_toolBarIcon)
    ImageView loginToolBarIcon;
    @BindView(R.id.login_et_phoneNum)
    EditText loginEtPhoneNum;
    @BindView(R.id.login_et_secretNum)
    EditText loginEtSecretNum;
    @BindView(R.id.login_btn_LogIn)
    Button loginBtnLogIn;
    @BindView(R.id.login_registerNum)
    TextView loginRegisterNum;
    @BindView(R.id.login_frogetNum)
    TextView loginFrogetNum;
    @BindView(R.id.login_userNeedKnow)
    TextView loginUserNeedKnow;
    private String phoneNum;
    private String secretNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void initView() {

        loginEtPhoneNum.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = s.toString().trim();
                if (trim.matches("[1][358]\\d{9}")) {
                    loginBtnLogIn.setEnabled(true);
                    loginBtnLogIn.getBackground().setAlpha(255);
                } else {
                    loginBtnLogIn.setEnabled(false);
                    loginBtnLogIn.getBackground().setAlpha(100);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.login_toolBarIcon,
            R.id.login_btn_LogIn,
            R.id.login_registerNum,
            R.id.login_frogetNum,
            R.id.login_userNeedKnow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_toolBarIcon://点击导航栏图标，返回上一个界面
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                break;

            case R.id.login_btn_LogIn://点击登录按键，验证用户名，密码，验证码

                logInbutton();//登录按键

                break;
            case R.id.login_registerNum://点击注册按键，跳转到注册界面
                startActivity(new Intent(this, RegisterActivity.class));
                //activity左出右进动画效果
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.login_frogetNum:
                Toast.makeText(this, "忘了就去注册！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_userNeedKnow:
                Toast.makeText(this, "用户协议：大家好才是真的好！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void logInbutton() {
        phoneNum = loginEtPhoneNum.getText().toString().trim();
        secretNum = loginEtSecretNum.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            return;
        } else {
            Log.i("TAG", "LogInActivity.logInbutton."+"feikong");
            if (phoneNum.matches("[1][358]\\d{9}")) {
                Log.i("TAG", "LogInActivity.logInbutton."+"feikong2");
                //查询数据


                List<RegisterPeople> peopleList = LiteOrmUtils
                        .getQueryByWhere(RegisterPeople.class, "number", new String[]{phoneNum});


                Log.i("TAG", "LogInActivity.logInbutton.peopleList.size()"+peopleList.size());

                if (peopleList!=null&&peopleList.size()!= 0) {
                    String password = peopleList.get(0).getPassword().toString();
                    String number = peopleList.get(0).getNumber();

                    Log.i("TAG", "LogInActivity.done." + password);
                    if (secretNum.equals(password)) {
                        Toast.makeText(LogInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        SharedPreferenceUtils.putAndApply(this, Flags.IsLogInFlag,true);
                        Intent intent = new Intent("success");
                        intent.putExtra("name", number);

                        sendBroadcast(intent);
                        finish();

                    } else {
                        Toast.makeText(LogInActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LogInActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private StringBuffer getUserName(String number) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(number.substring(0, 3));
        stringBuffer.append("****");
        stringBuffer.append(number.substring(7, 11));
        return stringBuffer;
    }
}
