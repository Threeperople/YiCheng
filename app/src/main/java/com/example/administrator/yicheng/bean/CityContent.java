package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jensen on 2016/8/6.
 */
public  class CityContent implements Serializable{
    @SerializedName("msgid")
    private String msgid;
    @SerializedName("docid")
    private String docid;
    @SerializedName("channelid")
    private String channelid;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("author")
    private String author;
    @SerializedName("images")
    private String images;
    @SerializedName("tags")
    private String tags;
    @SerializedName("mtime")
    private String mtime;
    @SerializedName("sendtime")
    private String sendtime;
    @SerializedName("st")
    private String st;
    @SerializedName("stype")
    private int stype;
    @SerializedName("summary")
    private String summary;
    @SerializedName("top_type")
    private String topType;
    @SerializedName("top_name")
    private String topName;
    @SerializedName("code")
    private String code;
    @SerializedName("number")
    private int number;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTopType() {
        return topType;
    }

    public void setTopType(String topType) {
        this.topType = topType;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
