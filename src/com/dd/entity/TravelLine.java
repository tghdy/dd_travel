package com.dd.entity;

import java.util.List;

public class TravelLine {
	private Integer id;//线路id

	private String travelNo;//线路编号

	private String travelName;//线路名

	private Integer trafficType;//交通方式 0=步行，1=汽车，2=火车，3=飞机，4=轮船，9=其他

	private Integer travelCount;//旅游人数

	private Integer travelOrderType;//订单类型 0：不需要审核 1：需要审核

	private Integer lineType;//线路类型

	private Integer travelDay;//旅游天数

	private Integer travelPrice;//旅游价格（成人）

	private Integer travelChildPrice;//儿童

	private Integer state;//状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTravelNo() {
		return travelNo;
	}

	public void setTravelNo(String travelNo) {
		this.travelNo = travelNo;
	}

	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}

	public Integer getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(Integer trafficType) {
		this.trafficType = trafficType;
	}

	public Integer getTravelCount() {
		return travelCount;
	}

	public void setTravelCount(Integer travelCount) {
		this.travelCount = travelCount;
	}

	public Integer getTravelOrderType() {
		return travelOrderType;
	}

	public void setTravelOrderType(Integer travelOrderType) {
		this.travelOrderType = travelOrderType;
	}

	public Integer getLineType() {
		return lineType;
	}

	public void setLineType(Integer lineType) {
		this.lineType = lineType;
	}

	public Integer getTravelDay() {
		return travelDay;
	}

	public void setTravelDay(Integer travelDay) {
		this.travelDay = travelDay;
	}

	public Integer getTravelPrice() {
		return travelPrice;
	}

	public void setTravelPrice(Integer travelPrice) {
		this.travelPrice = travelPrice;
	}

	public Integer getTravelChildPrice() {
		return travelChildPrice;
	}

	public void setTravelChildPrice(Integer travelChildPrice) {
		this.travelChildPrice = travelChildPrice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "TravelLine{" +
				"id=" + id +
				", travelNo='" + travelNo + '\'' +
				", travelName='" + travelName + '\'' +
				", trafficType=" + trafficType +
				", travelCount=" + travelCount +
				", travelOrderType=" + travelOrderType +
				", lineType=" + lineType +
				", travelDay=" + travelDay +
				", travelPrice=" + travelPrice +
				", travelChildPrice=" + travelChildPrice +
				", state=" + state +
				'}';
	}
	
	public Object[] params() {
		return new Object[]{id, travelNo, travelName, trafficType, travelCount, travelOrderType, lineType, 
				travelDay, travelPrice, travelChildPrice, state};
	}
	public Object[] updatePrams() {
		return new Object[]{travelNo, travelName, trafficType, travelCount, travelOrderType, lineType,
				travelDay, travelPrice, travelChildPrice, state, id};
	}
	
}