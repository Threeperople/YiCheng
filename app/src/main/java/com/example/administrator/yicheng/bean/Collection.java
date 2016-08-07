package com.example.administrator.yicheng.bean;


import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by Jensen on 2016/8/6.
 */
@Table("collection")
public class Collection implements Serializable{
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String url;
    private String title;
    private String Content;

    public Collection(String url, String title, String content) {
        this.url = url;
        this.title = title;
        Content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
