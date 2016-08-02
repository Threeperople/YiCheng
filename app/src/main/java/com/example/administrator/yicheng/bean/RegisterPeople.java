package com.example.administrator.yicheng.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/7/30.
 */
public class RegisterPeople extends BmobObject{
    private String  number;
    private String  password;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
