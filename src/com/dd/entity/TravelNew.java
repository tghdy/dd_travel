package com.dd.entity;

import java.util.Date;

public class TravelNew {
    private Integer id;

    private Integer category;

    private String title;
    
    private String createTime;

    private String lastEdit;

    private String  newsAuthor;

    private Integer newsView;

    private Integer newsPriority;

    private String newsContent;
    
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer catagory) {
        this.category = catagory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }

    public String  getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public Integer getNewsView() {
        return newsView;
    }

    public void setNewsView(Integer newsView) {
        this.newsView = newsView;
    }

    public Integer getNewsPriority() {
        return newsPriority;
    }

    public void setNewsPriority(Integer newsPriority) {
        this.newsPriority = newsPriority;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @Override
    public String toString() {
        return "TravelNew{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastEdit='" + lastEdit + '\'' +
                ", newsAuthor='" + newsAuthor + '\'' +
                ", newsView=" + newsView +
                ", newsPriority=" + newsPriority +
                ", newsContent='" + newsContent + '\'' +
                ", state=" + state +
                '}';
    }
}