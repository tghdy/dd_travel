package com.dd.entity;

public class TravelAdb {
	private Integer adbId;
	private Integer isAside;
	private String asidePicture;
	private Integer travelId;
	private TravelLine travelLine;

	public Integer getLine_type() {
		return line_type;
	}

	public void setLine_type(Integer line_type) {
		this.line_type = line_type;
	}

	private Integer line_type;


	public TravelLine getTravelLine() {
		return travelLine;
	}

	public void setTravelLine(TravelLine travelLine) {
		this.travelLine = travelLine;
	}

	public Integer getAdbId() {
		return adbId;
	}

	public void setAdbId(Integer adbId) {
		this.adbId = adbId;
	}

	public Integer getTravelId() {
		return travelId;
	}

	public void setTravelId(Integer travelId) {
		this.travelId = travelId;
	}

	public Integer getIsAside() {
		return isAside;
	}

	public void setIsAside(Integer isAside) {
		this.isAside = isAside;
	}

	public String getAsidePicture() {
		return asidePicture;
	}

	public void setAsidePicture(String asidePicture) {
		this.asidePicture = asidePicture;
	}

	@Override
	public String toString() {
		return "TravelAdb{" +
				"adbId=" + adbId +
				", isAside=" + isAside +
				", asidePicture='" + asidePicture + '\'' +
				", travelId=" + travelId +
				", travelLine=" + travelLine +
				", line_type=" + line_type +
				'}';
	}
}
