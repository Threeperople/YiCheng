package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jensen on 2016/8/3.
 */
public  class Title implements Serializable{
    @SerializedName("tid")
    private String tid;
    @SerializedName("mid")
    private String mid;
    @SerializedName("pic")
    private String pic;
    @SerializedName("articles")
    private int articles;
    @SerializedName("orders")
    private int orders;
    @SerializedName("head")
    private String head;
    @SerializedName("name")
    private String name;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getArticles() {
        return articles;
    }

    public void setArticles(int articles) {
        this.articles = articles;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
