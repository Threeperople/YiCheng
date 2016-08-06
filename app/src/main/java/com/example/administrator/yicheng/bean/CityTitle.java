package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jensen on 2016/8/6.
 */
public class CityTitle implements Serializable{
    @SerializedName("cid")
    private String cid;
    @SerializedName("gid")
    private String gid;
    @SerializedName("name")
    private String name;
    @SerializedName("ename")
    private String ename;
    @SerializedName("pos")
    private String pos;
    @SerializedName("isInitial")
    private String isInitial;
    @SerializedName("pic")
    private String pic;
    @SerializedName("head")
    private String head;
    @SerializedName("icon")
    private String icon;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getIsInitial() {
        return isInitial;
    }

    public void setIsInitial(String isInitial) {
        this.isInitial = isInitial;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
