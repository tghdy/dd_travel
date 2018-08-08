package com.dd.entity;


public class TravelUser {
    private long id;//id
    
    private String userAccount;//账号

    private String userName;//用户名

    private String password;//密码
    
    private String lastLogin;//最后登陆时间

    private String mobilePhone;//电话

    private String email;//邮箱
    
    private Integer sex;//性别
    
    private String idCard;//身份证

    private String head;//头像

    private Integer type;//类型

    private String permission;//权限集合

    private Integer state;//状态

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TravelUser{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", idCard='" + idCard + '\'' +
                ", head='" + head + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", state=" + state +
                '}';
    }

    public Object[] geParams() {
        Object[] objects = new Object[]{null, userAccount, userName, password, lastLogin, mobilePhone, email, sex, idCard, head, 0, 1, permission};
        return objects;
    }
    
}