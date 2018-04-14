package com.dd.entity;

public class TravelOrderDetail {
	private Long orderId;
	private Integer adultNum;
	private Integer childNum;
	private String orderRemarks;
	private String orderUser;
	private String orderMobile;
	private String orderEmail;
	private String orderIdcard;
	
	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public String getOrderRemarks() {
		return orderRemarks;
	}

	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}

	public String getOrderMobile() {
		return orderMobile;
	}

	public void setOrderMobile(String orderMobile) {
		this.orderMobile = orderMobile;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getOrderIdcard() {
		return orderIdcard;
	}

	public void setOrderIdcard(String orderIdcard) {
		this.orderIdcard = orderIdcard;
	}
}
