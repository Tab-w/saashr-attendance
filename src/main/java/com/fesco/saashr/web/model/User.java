package com.fesco.saashr.web.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String address;

    private Integer age;

    private String email;

    private String enabled;

    private String fullname;

    private String password;

    private String plainpassword;

    private String salt;

    private String sex;

    private Integer fid;

    private Integer type;

    private String username;

    private Integer org;

    private String userMobile;

    private String tid;

    private String remark;

    private Integer createUser;

    private String createTime;

    private String mNumber;

    private String pwdvalid;

    private String defPwd;

    private Integer startMenuId;

    private String pwdupdatetime;

    private Integer personId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPlainpassword() {
        return plainpassword;
    }

    public void setPlainpassword(String plainpassword) {
        this.plainpassword = plainpassword == null ? null : plainpassword.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getOrg() {
        return org;
    }

    public void setOrg(Integer org) {
        this.org = org;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber == null ? null : mNumber.trim();
    }

    public String getPwdvalid() {
        return pwdvalid;
    }

    public void setPwdvalid(String pwdvalid) {
        this.pwdvalid = pwdvalid == null ? null : pwdvalid.trim();
    }

    public String getDefPwd() {
        return defPwd;
    }

    public void setDefPwd(String defPwd) {
        this.defPwd = defPwd == null ? null : defPwd.trim();
    }

    public Integer getStartMenuId() {
        return startMenuId;
    }

    public void setStartMenuId(Integer startMenuId) {
        this.startMenuId = startMenuId;
    }

    public String getPwdupdatetime() {
        return pwdupdatetime;
    }

    public void setPwdupdatetime(String pwdupdatetime) {
        this.pwdupdatetime = pwdupdatetime == null ? null : pwdupdatetime.trim();
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", address=").append(address);
        sb.append(", age=").append(age);
        sb.append(", email=").append(email);
        sb.append(", enabled=").append(enabled);
        sb.append(", fullname=").append(fullname);
        sb.append(", password=").append(password);
        sb.append(", plainpassword=").append(plainpassword);
        sb.append(", salt=").append(salt);
        sb.append(", sex=").append(sex);
        sb.append(", fid=").append(fid);
        sb.append(", type=").append(type);
        sb.append(", username=").append(username);
        sb.append(", org=").append(org);
        sb.append(", userMobile=").append(userMobile);
        sb.append(", tid=").append(tid);
        sb.append(", remark=").append(remark);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", mNumber=").append(mNumber);
        sb.append(", pwdvalid=").append(pwdvalid);
        sb.append(", defPwd=").append(defPwd);
        sb.append(", startMenuId=").append(startMenuId);
        sb.append(", pwdupdatetime=").append(pwdupdatetime);
        sb.append(", personId=").append(personId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}