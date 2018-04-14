package com.dd.entity;

public class LinePlan {
    private Integer travelId;//线路id
    private Integer seq;//计划序列
    private String startTime;//计划时间
    private Integer planPrice;//计划价格
    private Integer planChildPrice;//计划儿童价格
    private String gatherTime;//集合时间
    private Integer gatherPlace;//集合地点
    private Integer dismissPlace;//解散地点

    public String getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(String gatherTime) {
        this.gatherTime = gatherTime;
    }

    public Integer getGatherPlace() {
        return gatherPlace;
    }

    public void setGatherPlace(Integer gatherPlace) {
        this.gatherPlace = gatherPlace;
    }

    public Integer getDismissPlace() {
        return dismissPlace;
    }

    public void setDismissPlace(Integer dismissPlace) {
        this.dismissPlace = dismissPlace;
    }

    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    @Override
    public String toString() {
        return "LinePlan{" +
                "travelId=" + travelId +
                ", seq=" + seq +
                ", startTime='" + startTime + '\'' +
                ", planPrice=" + planPrice +
                ", planChildPrice=" + planChildPrice +
                ", gatherTime='" + gatherTime + '\'' +
                ", gatherPlace=" + gatherPlace +
                ", dismissPlace=" + dismissPlace +
                '}';
    }

    public Object[] params() {
        return new Object[]{travelId, seq, startTime, planPrice, planChildPrice, gatherTime, gatherPlace, dismissPlace};
    }
    public Object[] updateParams() {
        return new Object[]{startTime, planPrice, planChildPrice, gatherTime, gatherPlace, dismissPlace, travelId, seq};
    }
    
}   