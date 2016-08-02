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
import com.example.administrator.yicheng.main.minef.login.register.RegisterActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/8/2.
 */
public class LogInActivity extends BaseActivity  {
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
                if(trim.matches("[1][358]\\d{9}")){
                    loginBtnLogIn.setEnabled(true);
                    loginBtnLogIn.getBackground().setAlpha(255);
                }else {
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
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
                break;

            case R.id.login_btn_LogIn://点击登录按键，验证用户名，密码，验证码

                    logInbutton();//登录按键

                break;
            case R.id.login_registerNum://点击注册按键，跳转到注册界面
                startActivity(new Intent(this,RegisterActivity.class));
                //activity左出右进动画效果
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;
            case R.id.login_frogetNum:
                Toast.makeText(this,"忘了就去注册！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_userNeedKnow:
                Toast.makeText(this,"用户协议：大家好才是真的好！",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void logInbutton() {
        phoneNum = loginEtPhoneNum.getText().toString().trim();
        secretNum = loginEtSecretNum.getText().toString().trim();
        if(TextUtils.isEmpty(phoneNum)){
            return;
        }else {
            if(phoneNum.matches("[1][358]\\d{9}")){
                //查询数据
                BmobQuery<RegisterPeople> bmobQuery = new BmobQuery<RegisterPeople>();

                bmobQuery.addWhereEqualTo("number", phoneNum);
                bmobQuery.findObjects(new FindListener<RegisterPeople>() {
                    @Override
                    public void done(List<RegisterPeople> list, BmobException e) {

                        if(e==null){
                            Log.i("TAG", "LogInActivity.done.poneNum+secretNum"+phoneNum+secretNum);
                            if(list.size()!=0){
                                String password = list.get(0).getPassword();
                                String number = list.get(0).getNumber();
                                Log.i("TAG", "LogInActivity.done."+password);
                                if(secretNum.equals(password)){
                                    Toast.makeText(LogInActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent("success");
//                                    StringBuffer userName = getUserName(number);//自制用户名
                                    intent.putExtra("name",number);


                                    sendBroadcast(intent);

                                    finish();

                                }else {
                                    Toast.makeText(LogInActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LogInActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(LogInActivity.this,"网络异常，请检查网络!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else{
                return;
            }
        }
    }

    private StringBuffer getUserName(String number) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(number.substring(0,3));
        stringBuffer.append("****");
        stringBuffer.append(number.substring(7,11));
        return stringBuffer;
    }
}
