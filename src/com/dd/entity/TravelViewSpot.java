package com.dd.entity;

public class TravelViewSpot {
    private Integer id;

    private String viewName;

    private String viewDesc;

    private String viewPhoto;

    private Integer placeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName == null ? null : viewName.trim();
    }

    public String getViewDesc() {
        return viewDesc;
    }

    public void setViewDesc(String viewDesc) {
        this.viewDesc = viewDesc == null ? null : viewDesc.trim();
    }

    public String getViewPhoto() {
        return viewPhoto;
    }

    public void setViewPhoto(String viewPhoto) {
        this.viewPhoto = viewPhoto == null ? null : viewPhoto.trim();
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }
}