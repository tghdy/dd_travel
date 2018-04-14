package com.dd.entity;

public class LineLabel {
    private Integer id;

    private String labelName;

    private String labelColor;

    private String createTime;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LineLabel{" +
                "id=" + id +
                ", labelName='" + labelName + '\'' +
                ", labelColor='" + labelColor + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                '}';
    }
}