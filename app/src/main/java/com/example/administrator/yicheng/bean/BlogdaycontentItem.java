package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

public  class BlogdaycontentItem {
        @SerializedName("id")
        private String id;
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
        @SerializedName("url")
        private String url;
        @SerializedName("images")
        private String images;
        @SerializedName("source")
        private String source;
        @SerializedName("mtime")
        private String mtime;
        @SerializedName("utime")
        private String utime;
        @SerializedName("sendtime")
        private String sendtime;
        @SerializedName("day")
        private String day;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }
    }