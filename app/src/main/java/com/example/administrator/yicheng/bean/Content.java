package com.example.administrator.yicheng.bean;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Jensen on 2016/8/3.
 */

public  class Content {
        @SerializedName("msgid")
        private String msgid;
        @SerializedName("channelid")
        private String channelid;
        @SerializedName("title")
        private String title;
        @SerializedName("url")
        private String url;
        @SerializedName("content")
        private String content;
        @SerializedName("icon")
        private String icon;
        @SerializedName("summary")
        private String summary;
        @SerializedName("page")
        private String page;
        @SerializedName("mtime")
        private String mtime;
        @SerializedName("jid")
        private String jid;
        @SerializedName("jdata")
        private String jdata;
        @SerializedName("st")
        private String st;
        @SerializedName("pics")
        private String pics;
        @SerializedName("tags")
        private String tags;
        @SerializedName("ltag")
        private String ltag;
        @SerializedName("json_type")
        private String jsonType;
        @SerializedName("msg_type")
        private int msgType;
        @SerializedName("top_type")
        private String topType;
        @SerializedName("top_name")
        private String topName;
        @SerializedName("rec_type")
        private int recType;
        @SerializedName("cat_id")
        private String catId;
        @SerializedName("stype")
        private int stype;

        public String getMsgid() {
            return msgid;
        }

        public void setMsgid(String msgid) {
            this.msgid = msgid;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public String getJid() {
            return jid;
        }

        public void setJid(String jid) {
            this.jid = jid;
        }

        public String getJdata() {
            return jdata;
        }

        public void setJdata(String jdata) {
            this.jdata = jdata;
        }

        public String getSt() {
            return st;
        }

        public void setSt(String st) {
            this.st = st;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getLtag() {
            return ltag;
        }

        public void setLtag(String ltag) {
            this.ltag = ltag;
        }

        public String getJsonType() {
            return jsonType;
        }

        public void setJsonType(String jsonType) {
            this.jsonType = jsonType;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
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

        public int getRecType() {
            return recType;
        }

        public void setRecType(int recType) {
            this.recType = recType;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public int getStype() {
            return stype;
        }

        public void setStype(int stype) {
            this.stype = stype;
        }
    }

