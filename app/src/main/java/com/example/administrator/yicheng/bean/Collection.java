package com.example.administrator.yicheng.bean;


import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Jensen on 2016/8/6.
 */
@Table("collection")
public class Collection {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private CityContent cityContent;

    private BlogdaycontentItem blogdaycontentItem;

    private Content content;

    public Collection( CityContent cityContent, BlogdaycontentItem blogdaycontentItem, Content content) {
        this.cityContent = cityContent;
        this.blogdaycontentItem = blogdaycontentItem;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CityContent getCityContent() {
        return cityContent;
    }

    public void setCityContent(CityContent cityContent) {
        this.cityContent = cityContent;
    }

    public BlogdaycontentItem getBlogdaycontentItem() {
        return blogdaycontentItem;
    }

    public void setBlogdaycontentItem(BlogdaycontentItem blogdaycontentItem) {
        this.blogdaycontentItem = blogdaycontentItem;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
