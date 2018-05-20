package com.dd.entity;

public class LinePlan {
    private Integer id; 
    private Integer travelId;//线路id
    private String startTime;//计划时间
    private Integer planPrice;//计划价格
    private Integer planChildPrice;//计划儿童价格
    private String gatherTime;//集合时间
    private String gatherPlace;//集合地点
    private String dismissPlace;//解散地点

    public String getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(String gatherTime) {
        this.gatherTime = gatherTime;
    }

    public String getGatherPlace() {
        return gatherPlace;
    }

    public void setGatherPlace(String gatherPlace) {
        this.gatherPlace = gatherPlace;
    }

    public String getDismissPlace() {
        return dismissPlace;
    }

    public void setDismissPlace(String dismissPlace) {
        this.dismissPlace = dismissPlace;
    }

    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Integer planPrice) {
        this.planPrice = planPrice;
    }

    public Integer getPlanChildPrice() {
        return planChildPrice;
    }

    public void setPlanChildPrice(Integer planChildPrice) {
        this.planChildPrice = planChildPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Object[] params() {
        return new Object[]{id, travelId, startTime, planPrice, planChildPrice};
    }

    public Object[] updateParams() {
        return new Object[]{startTime, planPrice, planChildPrice, gatherTime, gatherPlace, dismissPlace, id};
    }
}   