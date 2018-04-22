package com.dd.entity;

public class TravelOrder {
	private Long id;
	private String orderNo;//订单编号
	private Integer orderState;//订单状态
	private Long userId;//订单用户id
	private Integer travelId;//线路id
	private Integer planSeq;//班次序号
	private int orderPrice;//订单价格
	private String orderTime;//订单生成时间
	private Integer invalidEmp;//作废人员
	private String invalidTime;//订单作废时间
	private Integer auditEmp;//审核人员
	private String auditTime;//审核时间
	//public TravelLine travelLine; //旅游线路对象
	//public TravelUser travelUser;//订单用户对象
	//id
	//		order_no
	//order_state
	//		user_id
	//travel_id
	//		order_price
	//order_time
	//		invalid_emp
	//invalid_time
	//		audit_emp
	//audit_time
	//		travel_line

	public TravelOrder() {
	}

	public Integer getPlanSeq() {
		return planSeq;
	}

	public void setPlanSeq(Integer planSeq) {
		this.planSeq = planSeq;
	}

	@Override
	public String toString() {
		return "TravelOrder{" +
				"id=" + id +
				", orderNo=" + orderNo +
				", orderState=" + orderState +
				", userId=" + userId +
				", travelId=" + travelId +
				", orderPrice=" + orderPrice +
				", orderTime='" + orderTime + '\'' +
				", invalidEmp=" + invalidEmp +
				", invalidTime='" + invalidTime + '\'' +
				", auditEmp=" + auditEmp +
				", auditTime='" + auditTime + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getTravelId() {
		return travelId;
	}

	public void setTravelId(Integer travelId) {
		this.travelId = travelId;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getInvalidEmp() {
		return invalidEmp;
	}

	public void setInvalidEmp(Integer invalidEmp) {
		this.invalidEmp = invalidEmp;
	}

	public String getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}

	public Integer getAuditEmp() {
		return auditEmp;
	}

	public void setAuditEmp(Integer auditEmp) {
		this.auditEmp = auditEmp;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
}