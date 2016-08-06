package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jensen on 2016/8/6.
 */
public class CityTitleBean {

    /**
     * result : true
     * data : [{"cid":"128","gid":"6","name":"玩乐","ename":"","pos":"7","isInitial":"1","pic":"http://www.cityfun.me/Public/img/yunying/128wanzhuanshenzhen/wanle.jpg","head":"http://www.cityfun.me/Public/img/yunying/catory/wanle.jpg","icon":""},{"cid":"125","gid":"5","name":"寻味","ename":"","pos":"4","isInitial":"1","pic":"http://www.cityfun.me/Public/img/yunying/125shenzhenxunwei/xunwei.jpg","head":"http://www.cityfun.me/Public/img/yunying/catory/xunwei.jpg","icon":"https://www.cityfun.me/Public/img/hot.png"}]
     */

    @SerializedName("result")
    private boolean result;
    /**
     * cid : 128
     * gid : 6
     * name : 玩乐
     * ename :
     * pos : 7
     * isInitial : 1
     * pic : http://www.cityfun.me/Public/img/yunying/128wanzhuanshenzhen/wanle.jpg
     * head : http://www.cityfun.me/Public/img/yunying/catory/wanle.jpg
     * icon :
     */

    @SerializedName("data")
    private List<CityTitle> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<CityTitle> getData() {
        return data;
    }

    public void setData(List<CityTitle> data) {
        this.data = data;
    }


}
