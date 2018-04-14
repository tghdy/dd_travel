package com.dd.dto;

import javax.naming.Name;

public class LineSearchItem {
	private String title;
	private Integer sort;//0升序，1降序，2默认
	private Integer lineType;
	private String toName;
	private String toPname;
	private Integer day;

	public Integer getLineType() {
		return lineType;
	}

	public void setLineType(Integer lineType) {
		this.lineType = lineType;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getToPname() {
		return toPname;
	}

	public void setToPname(String toPname) {
		this.toPname = toPname;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "LineSearchItem{" +
				"title='" + title + '\'' +
				", sort=" + sort +
				", lineType=" + lineType +
				", toName='" + toName + '\'' +
				", toPname='" + toPname + '\'' +
				", day=" + day +
				'}';
	}
}
