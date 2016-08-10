package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

public  class Profile {
        @SerializedName("atid")
        private String atid;
        @SerializedName("title")
        private String title;
        @SerializedName("tagurl")
        private String tagurl;
        @SerializedName("imgurl")
        private String imgurl;
        @SerializedName("city")
        private String city;
        @SerializedName("surpcount")
        private String surpcount;
        @SerializedName("joincount")
        private String joincount;
        @SerializedName("expid")
        private String expid;
        @SerializedName("expname")
        private String expname;
        @SerializedName("addr")
        private String addr;
        @SerializedName("cost")
        private String cost;
        @SerializedName("unit")
        private String unit;
        @SerializedName("showtime")
        private String showtime;
        @SerializedName("exphead")
        private String exphead;
        @SerializedName("gourl")
        private String gourl;
        @SerializedName("status")
        private int status;
        @SerializedName("atime")
        private String atime;

        public String getAtid() {
            return atid;
        }

        public void setAtid(String atid) {
            this.atid = atid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTagurl()    {
        return tagurl;
    }

        public void setTagurl(String tagurl) {
            this.tagurl = tagurl;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSurpcount() {
            return surpcount;
        }

        public void setSurpcount(String surpcount) {
            this.surpcount = surpcount;
        }

        public String getJoincount() {
            return joincount;
        }

        public void setJoincount(String joincount) {
            this.joincount = joincount;
        }

        public String getExpid() {
            return expid;
        }

        public void setExpid(String expid) {
            this.expid = expid;
        }

        public String getExpname() {
            return expname;
        }

        public void setExpname(String expname) {
            this.expname = expname;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getShowtime() {
            return showtime;
        }

        public void setShowtime(String showtime) {
            this.showtime = showtime;
        }

        public String getExphead() {
            return exphead;
        }

        public void setExphead(String exphead) {
            this.exphead = exphead;
        }

        public String getGourl() {
            return gourl;
        }

        public void setGourl(String gourl) {
            this.gourl = gourl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAtime() {
            return atime;
        }

        public void setAtime(String atime) {
            this.atime = atime;
        }
    }