package com.dd.dto;

public class UserSearchItem {
	private String name;
	private String phone;
	private String email;
	private Integer state;
	private Integer startIndex;


	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "UserSearchItem{" +
				"name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", state=" + state +
				", startIndex=" + startIndex +
				'}';
	}
}
