package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jensen on 2016/7/29.
 */
public class TitleBean {


    /**
     * result : true
     * data : [{"tid":"414","mid":"123","pic":"https://www.cityfun.me/Public/img/yunying/123yanchuyl/yanchuyule10312.jpg","articles":231,"orders":881,"head":"http://www.cityfun.me/Public/img/yunying/catory/yanchuyule10223.jpg","name":"演出娱乐"},{"tid":"426","mid":"129","pic":"https://www.cityfun.me/Public/img/yunying/129zhoubianyou/zhoubianyou11056.jpg","articles":203,"orders":537,"head":"http://www.cityfun.me/Public/img/yunying/catory/zhoubianyou11051.jpg","name":"周边游"},{"tid":"396","mid":"114","pic":"https://www.cityfun.me/Public/img/yunying/114chihuoxiuy/ystk.jpg","articles":272,"orders":1067,"head":"http://www.cityfun.me/Public/img/yunying/catory/chihuoxiuy1.jpg","name":"饮食谈客"},{"tid":"410","mid":"121","pic":"https://www.cityfun.me/Public/img/yunying/121buxunczy/yzhs.jpg","articles":200,"orders":937,"head":"http://www.cityfun.me/Public/img/yunying/catory/buxunczy.jpg","name":"圆桌说"},{"tid":"420","mid":"126","pic":"https://www.cityfun.me/Public/img/yunying/126haowu/haowu11051417.jpg","articles":348,"orders":936,"head":"http://www.cityfun.me/Public/img/yunying/catory/haowu11053.jpg","name":"好物"},{"tid":"422","mid":"127","pic":"https://www.cityfun.me/Public/img/yunying/127jujia/jujia110505.jpg","articles":341,"orders":853,"head":"http://www.cityfun.me/Public/img/yunying/catory/jiaju11054.jpg","name":"居家"},{"tid":"432","mid":"132","pic":"https://www.cityfun.me/Public/img/yunying/132chuandass/chuandass.jpg","articles":147,"orders":949,"head":"http://www.cityfun.me/Public/img/yunying/catory/shishangcd.png","name":"女王衣橱"},{"tid":"434","mid":"133","pic":"https://www.cityfun.me/Public/img/yunying/133caizhuanghf/caizhuanghf.jpg","articles":112,"orders":504,"head":"http://www.cityfun.me/Public/img/yunying/catory/caizhuanghf.png","name":"彩妆护肤"},{"tid":"436","mid":"134","pic":"https://www.cityfun.me/Public/img/yunying/134nanrenz/nanrenz.jpg","articles":123,"orders":480,"head":"http://www.cityfun.me/Public/img/yunying/catory/nanrenz.png","name":"男人装"},{"tid":"438","mid":"135","pic":"https://www.cityfun.me/Public/img/yunying/135dapaijd/dapaijd.jpg","articles":79,"orders":454,"head":"http://www.cityfun.me/Public/img/yunying/catory/dapaijd.png","name":"大牌驾到"},{"tid":"524","mid":"178","pic":"https://www.cityfun.me/Public/img/yunying/178bense/bense.jpg","articles":78,"orders":143,"head":"http://www.cityfun.me/Public/img/yunying/catory/bense.jpg","name":"本色"},{"tid":"526","mid":"179","pic":"https://www.cityfun.me/Public/img/yunying/179fys/fys.jpg","articles":54,"orders":68,"head":"http://www.cityfun.me/Public/img/yunying/catory/fys.jpg","name":"放映室"}]
     */

    @SerializedName("result")
    private boolean result;
    /**
     * tid : 414
     * mid : 123
     * pic : https://www.cityfun.me/Public/img/yunying/123yanchuyl/yanchuyule10312.jpg
     * articles : 231
     * orders : 881
     * head : http://www.cityfun.me/Public/img/yunying/catory/yanchuyule10223.jpg
     * name : 演出娱乐
     */

    @SerializedName("data")
    private List<Title> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<Title> getData() {
        return data;
    }

    public void setData(List<Title> data) {
        this.data = data;
    }

}
