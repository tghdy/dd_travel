package com.dd.entity;

public class LineDetailEn {
    private Integer travelId;

    private String travelSubtitle;

    private String travelFeature;

    private String travelTips;

    private Integer travelFrom;

    private Integer travelTo;

    private Integer travelViews;

    private String lineLabels;

    private String travelInfo;

    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }

    public String getTravelSubtitle() {
        return travelSubtitle;
    }

    public void setTravelSubtitle(String travelSubtitle) {
        this.travelSubtitle = travelSubtitle == null ? null : travelSubtitle.trim();
    }

    public String getTravelFeature() {
        return travelFeature;
    }

    public void setTravelFeature(String travelFeature) {
        this.travelFeature = travelFeature == null ? null : travelFeature.trim();
    }

    public String getTravelTips() {
        return travelTips;
    }

    public void setTravelTips(String travelTips) {
        this.travelTips = travelTips == null ? null : travelTips.trim();
    }

    public Integer getTravelFrom() {
        return travelFrom;
    }

    public void setTravelFrom(Integer travelFrom) {
        this.travelFrom = travelFrom;
    }

    public Integer getTravelTo() {
        return travelTo;
    }

    public void setTravelTo(Integer travelTo) {
        this.travelTo = travelTo;
    }

    public Integer getTravelViews() {
        return travelViews;
    }

    public void setTravelViews(Integer travelViews) {
        this.travelViews = travelViews;
    }

    public String getLineLabels() {
        return lineLabels;
    }

    public void setLineLabels(String lineLabels) {
        this.lineLabels = lineLabels == null ? null : lineLabels.trim();
    }

    public String getTravelInfo() {
        return travelInfo;
    }

    public void setTravelInfo(String travelInfo) {
        this.travelInfo = travelInfo == null ? null : travelInfo.trim();
    }
}