package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jensen on 2016/8/5.
 */
public class CityBean {

    /**
     * result : true
     * data : [{"id":"1","cityname":"上海","citycode":"021","ename":"Shanghai","status":"1","images":"http://www.cityfun.me/Public/img/yunying/cityIcon/icon_shanghai@3x.png","isHot":"1","pos":"3"},{"id":"2","cityname":"北京","citycode":"010","ename":"Beijing","status":"1","images":"http://www.cityfun.me/Public/img/yunying/cityIcon/icon_beijing@3x.png","isHot":"1","pos":"4"},{"id":"3","cityname":"广州","citycode":"020","ename":"Guangzhou","status":"1","images":"http://www.cityfun.me/Public/img/yunying/cityIcon/icon_guangzhou@3x.png","isHot":"1","pos":"2"},{"id":"4","cityname":"武汉","citycode":"027","ename":"Wuhan","status":"1","images":"","isHot":"0","pos":"0"},{"id":"5","cityname":"成都","citycode":"028","ename":"Chengdu","status":"1","images":"","isHot":"0","pos":"0"},{"id":"6","cityname":"杭州","citycode":"0571","ename":"Hangzhou","status":"1","images":"","isHot":"0","pos":"0"},{"id":"7","cityname":"西安","citycode":"029","ename":"Xi'an","status":"1","images":"","isHot":"0","pos":"0"},{"id":"8","cityname":"南京","citycode":"025","ename":"Nanjing","status":"1","images":"","isHot":"0","pos":"0"},{"id":"9","cityname":"重庆","citycode":"023","ename":"Chongqing","status":"1","images":"","isHot":"0","pos":"0"},{"id":"10","cityname":"天津","citycode":"022","ename":"Tianjin","status":"1","images":"","isHot":"0","pos":"0"},{"id":"11","cityname":"深圳","citycode":"0755","ename":"Shenzhen","status":"1","images":"http://www.cityfun.me/Public/img/yunying/cityIcon/icon_shenzhen@3x.png","isHot":"1","pos":"1"}]
     */

    @SerializedName("result")
    private boolean result;
    /**
     * id : 1
     * cityname : 上海
     * citycode : 021
     * ename : Shanghai
     * status : 1
     * images : http://www.cityfun.me/Public/img/yunying/cityIcon/icon_shanghai@3x.png
     * isHot : 1
     * pos : 3
     */

    @SerializedName("data")
    private List<City> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }


}
