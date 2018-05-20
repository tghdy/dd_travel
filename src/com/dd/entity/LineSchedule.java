package com.dd.entity;

import javax.xml.soap.Detail;

public class LineSchedule {
    private Integer travelId;

    private Integer seq;

    private String scheDetail;

    private Integer scheStayLevel;

    private String stayHotel;

    private Integer scheMeal;

    private Integer scheMeal2;

    private Integer scheMeal3;

    private String scheViews;

    private String schePic;
    
    private String schePic2;
    
    private String schePic3;

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

    public String getScheDetail() {
        return scheDetail;
    }

    public void setScheDetail(String scheDetail) {
        this.scheDetail = scheDetail;
    }

    public Integer getScheStayLevel() {
        return scheStayLevel;
    }

    public void setScheStayLevel(Integer scheStayLevel) {
        this.scheStayLevel = scheStayLevel;
    }

    public String getStayHotel() {
        return stayHotel;
    }

    public void setStayHotel(String stayHotel) {
        this.stayHotel = stayHotel;
    }

    public Integer getScheMeal() {
        return scheMeal;
    }

    public void setScheMeal(Integer scheMeal) {
        this.scheMeal = scheMeal;
    }

    public Integer getScheMeal2() {
        return scheMeal2;
    }

    public void setScheMeal2(Integer scheMeal2) {
        this.scheMeal2 = scheMeal2;
    }

    public Integer getScheMeal3() {
        return scheMeal3;
    }

    public void setScheMeal3(Integer scheMeal3) {
        this.scheMeal3 = scheMeal3;
    }

    public String getScheViews() {
        return scheViews;
    }

    public void setScheViews(String scheViews) {
        this.scheViews = scheViews;
    }

    public String getSchePic() {
        return schePic;
    }

    public void setSchePic(String schePic) {
        this.schePic = schePic;
    }

    public String getSchePic2() {
        return schePic2;
    }

    public void setSchePic2(String schePic2) {
        this.schePic2 = schePic2;
    }

    public String getSchePic3() {
        return schePic3;
    }

    public void setSchePic3(String schePic3) {
        this.schePic3 = schePic3;
    }

    @Override
    public String toString() {
        return "LineSchedule{" +
                "travelId=" + travelId +
                ", seq=" + seq +
                ", scheDetail='" + scheDetail + '\'' +
                ", scheStayLevel=" + scheStayLevel +
                ", stayHotel='" + stayHotel + '\'' +
                ", scheMeal=" + scheMeal +
                ", scheMeal2=" + scheMeal2 +
                ", scheMeal3=" + scheMeal3 +
                ", scheViews='" + scheViews + '\'' +
                ", schePic='" + schePic + '\'' +
                ", schePic2='" + schePic2 + '\'' +
                ", schePic3='" + schePic3 + '\'' +
                '}';
    }

    public Object[] params() {
        return new Object[]{
            travelId, seq, scheDetail, scheStayLevel,
            stayHotel, scheMeal, scheMeal2, scheMeal3, scheViews, schePic, schePic2, schePic3
        };
    }

    public Object[] updateParams() {
        return new Object[]{scheDetail, scheStayLevel,
                stayHotel, scheMeal, scheMeal2, scheMeal3, scheViews, schePic, schePic2, schePic3,
                travelId, seq};
    }
}