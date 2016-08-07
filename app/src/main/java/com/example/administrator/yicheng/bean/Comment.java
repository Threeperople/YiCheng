package com.example.administrator.yicheng.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Jensen on 2016/8/7.
 */
@Table("comment")
public class Comment {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String url;
    private String word;
    private long time;
    private String name;

    public Comment(String url, String word, long time, String name) {
        this.url = url;
        this.word = word;
        this.time = time;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
