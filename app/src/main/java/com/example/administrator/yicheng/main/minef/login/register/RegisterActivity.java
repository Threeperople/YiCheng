package com.example.administrator.yicheng.main.minef.login.register;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.example.administrator.yicheng.utils.SharedPreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/8/2.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.Register_et_PhoneNum)
    EditText RegisterEtPhoneNum;
    @BindView(R.id.Register_button_GetVerifycode)
    Button RegisterButtonGetVerifycode;
    @BindView(R.id.Register_et_secretCode)
    EditText RegisterEtSecretCode;
    @BindView(R.id.register_image_Lock)
    ImageView registerImageLock;
    @BindView(R.id.Register_et_InputVerifyCode)
    EditText RegisterEtInputVerifyCode;

    private static final String MODE_NAME="message";
    private Boolean LockImageFlag= true;
    private String phoneNum;

    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int i = (int) msg.obj;
                    if(i==1){
                        RegisterButtonGetVerifycode.setText("重新获取");
                    }else{
                        RegisterButtonGetVerifycode.setText(String.valueOf(i)+"S");
                    }
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.Register_toolBarIcon, R.id.Register_button_GetVerifycode,
            R.id.register_image_Lock, R.id.Register_button_ToRegister, R.id.Register_tv_userNeedKnow})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.Register_toolBarIcon:
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
                break;

            case R.id.Register_button_GetVerifycode:
                String phone = RegisterEtPhoneNum.getText().toString().trim();
                BmobSMS.requestSMSCode(RegisterActivity.this, phone,MODE_NAME,new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer smsId,BmobException ex) {
                        if(ex==null){//验证码发送成功
                            Log.i("bmob", "短信id："+smsId);//用于查询本次短信发送详情
                        }
                    }
                });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=60;i>0;i--){
                            try {
                                Thread.sleep(1000);
                                Message message = mHandler.obtainMessage(1,i);
                                mHandler.sendMessage(message);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;

            //点击设置密码是否可见
            case R.id.register_image_Lock:
                LockImageFlag=!LockImageFlag;
                if(LockImageFlag){
                    registerImageLock.setImageResource(R.drawable.register_icon_unview);
                    RegisterEtSecretCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    registerImageLock.setImageResource(R.drawable.register_icon_view);
                    RegisterEtSecretCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;

            case R.id.Register_button_ToRegister:
                phoneNum = RegisterEtPhoneNum.getText().toString().trim();
              final String secretCode = RegisterEtSecretCode.getText().toString().trim();
               String  verifyCode = RegisterEtInputVerifyCode.getText().toString().trim();
                if(TextUtils.isEmpty(phoneNum)||TextUtils.isEmpty(secretCode)||TextUtils.isEmpty(verifyCode)){
                    showSnackBar(view,"以上信息不能为空！");
                }else {
                    Log.i("bmob", "RegisterActivity.toRegister."+ phoneNum +secretCode+verifyCode);
                    BmobSMS.verifySmsCode(RegisterActivity.this, phoneNum, verifyCode, new VerifySMSCodeListener() {
                        @Override
                        public void done(BmobException ex) {
                            Log.i("bmob", "K开始验证");

                            if(ex==null){//短信验证码已验证成功
                                Log.i("bmob", "验证通过");
                                if(phoneNum !=null&&secretCode!=null){
                                    RegisterPeople people = new RegisterPeople();
                                    people.setNumber(phoneNum);
                                    people.setPassword(secretCode);
                                    save(people);//验证成功后，向服务端添加数据
                                }else{
                                    showSnackBar(view,"以上信息不能为空！");
                                }
                            }else{//验证码错误
                                showSnackBar(view,"验证码错误！");
                            }
                        }
                    });
                }

                break;
            case R.id.Register_tv_userNeedKnow://用户协议
                //TODO
                break;
        }
    }

    private void showSnackBar(View view,String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        View view1 = snackbar.getView();    //获得弹出视图对象
        view1.setBackgroundColor(getResources().getColor(R.color.colortheTheme));//设置该视图的背景
        snackbar.setActionTextColor(Color.RED).show();//文字颜色

    }
    private void save(RegisterPeople people) {//验证成功后，向服务端添加数据
        people.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, cn.bmob.v3.exception.BmobException e) {
                if(e==null){
                    //  toast("添加数据成功，返回objectId为："+objectId);
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    //注册成功，将数据保存到本地，name,sex,toux;
                    //key:电话号码----生成用户名
                    SharedPreferenceUtils.putAndApply(RegisterActivity.this, phoneNum, getUserName(phoneNum));
                    //key:生成的用户名---->sex性别
                    SharedPreferenceUtils.putAndApply(RegisterActivity.this,getUserName(phoneNum).toString(),"保密");
                    finish();
                }else{
                    // toast("创建数据失败：" + e.getMessage());
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private StringBuffer getUserName(String number) {//自动生成用户名
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(number.substring(0,3));
        stringBuffer.append("****");
        stringBuffer.append(number.substring(7,11));
        return stringBuffer;
    }
}
