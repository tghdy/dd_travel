package com.dd.entity;

public class TravelPlace {
    private Integer id;
    private Integer parentId;
    private String placeName;
    private String placeDesc;
    private String placePhoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getPlaceDesc() {
        return placeDesc;
    }

    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc == null ? null : placeDesc.trim();
    }

    public String getPlacePhoto() {
        return placePhoto;
    }

    public void setPlacePhoto(String placePhoto) {
        this.placePhoto = placePhoto == null ? null : placePhoto.trim();
    }

    @Override
    public String toString() {
        return "TravelPlace{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", placeName='" + placeName + '\'' +
                ", placeDesc='" + placeDesc + '\'' +
                ", placePhoto='" + placePhoto + '\'' +
                '}';
    }
}