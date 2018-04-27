package com.dd.entity;

public class LineDetail {
	private Long travelId;//线路id

	private String travelSubtitle;//线路副标题

	private String travelFeature;//线路特色

	private String travelTips;//线路提示

	private Integer travelFrom;//线路出发地主键

	private Integer travelTo;//线路目的地主键

	private Integer travelViews;//线路浏览人数

	private String lineLabels;//线路标签

	private String travelInfo;//线路信息
	
	private String travelPicture;//线路首图
	
	private String travelPicture2;//线路图2
	
	private String travelPicture3;//线路图3
	
	private String travelPicture4;//线路图4

	private String schedulesPdf;//日程pdf地址
	
	public Long getTravelId() {
		return travelId;
	}

	public void setTravelId(Long travelId) {
		this.travelId = travelId;
	}

	public String getTravelSubtitle() {
		return travelSubtitle;
	}

	public void setTravelSubtitle(String travelSubtitle) {
		this.travelSubtitle = travelSubtitle;
	}

	public String getTravelFeature() {
		return travelFeature;
	}

	public void setTravelFeature(String travelFeature) {
		this.travelFeature = travelFeature;
	}

	public String getTravelTips() {
		return travelTips;
	}

	public void setTravelTips(String travelTips) {
		this.travelTips = travelTips;
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
		this.lineLabels = lineLabels;
	}

	public String getTravelInfo() {
		return travelInfo;
	}

	public void setTravelInfo(String travelInfo) {
		this.travelInfo = travelInfo;
	}

	public String getTravelPicture() {
		return travelPicture;
	}

	public void setTravelPicture(String travelPicture) {
		this.travelPicture = travelPicture;
	}

	public String getTravelPicture2() {
		return travelPicture2;
	}

	public void setTravelPicture2(String travelPicture2) {
		this.travelPicture2 = travelPicture2;
	}

	public String getTravelPicture3() {
		return travelPicture3;
	}

	public void setTravelPicture3(String travelPicture3) {
		this.travelPicture3 = travelPicture3;
	}

	public String getTravelPicture4() {
		return travelPicture4;
	}

	public void setTravelPicture4(String travelPicture4) {
		this.travelPicture4 = travelPicture4;
	}

	public String getSchedulesPdf() {
		return schedulesPdf;
	}

	public void setSchedulesPdf(String schedulesPdf) {
		this.schedulesPdf = schedulesPdf;
	}

	@Override
	public String toString() {
		return "LineDetail{" +
				"travelId=" + travelId +
				", travelSubtitle='" + travelSubtitle + '\'' +
				", travelFeature='" + travelFeature + '\'' +
				", travelTips='" + travelTips + '\'' +
				", travelFrom=" + travelFrom +
				", travelTo=" + travelTo +
				", travelViews=" + travelViews +
				", lineLabels='" + lineLabels + '\'' +
				", travelInfo='" + travelInfo + '\'' +
				", travelPicture='" + travelPicture + '\'' +
				", travelPicture2='" + travelPicture2 + '\'' +
				", travelPicture3='" + travelPicture3 + '\'' +
				", travelPicture4='" + travelPicture4 + '\'' +
				'}';
	}

	public Object[] updateParams() {
		return new Object[]{travelSubtitle, travelFeature, travelTips, travelFrom, travelTo, travelViews, lineLabels, 
				travelInfo, travelPicture,  travelPicture2,  travelPicture3,  travelPicture4, schedulesPdf, travelId};
	}
	
}