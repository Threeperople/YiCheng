package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jensen on 2016/8/5.
 */
public  class City implements Comparable<City>,Serializable{
    @SerializedName("id")
    private String id;
    @SerializedName("cityname")
    private String cityname;
    @SerializedName("citycode")
    private String citycode;
    @SerializedName("ename")
    private String ename;
    @SerializedName("status")
    private String status;
    @SerializedName("images")
    private String images;
    @SerializedName("isHot")
    private String isHot;
    @SerializedName("pos")
    private String pos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }



    @Override
    public int compareTo(City another) {
        int i=this.getEname().substring(0,1).hashCode();
        int j=another.getEname().substring(0,1).hashCode();
        return i-j;
    }
}
