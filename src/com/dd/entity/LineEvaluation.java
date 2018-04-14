package com.dd.entity;

import java.util.Date;

public class LineEvaluation {
	private Integer id;

	private Integer travelId;

	private Integer userId;

	private String evaluation;

	private Integer evaluateStar;

	private Date evaluateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTravelId() {
		return travelId;
	}

	public void setTravelId(Integer travelId) {
		this.travelId = travelId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluateStar = evaluateStar;
	}

	public Integer getEvaluateStar() {
		return evaluateStar;
	}

	public void setEvaluateStar(Integer evaluateStar) {
		this.evaluateStar = evaluateStar;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
}