package com.stla.figure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class xdUserStatistics  implements Serializable {

    private static final long serialVersionUID = -7603950849911602029L;

    private Integer id;

    private Integer userId;

    private Integer opusAllNumber;

    private Integer opusAllTime;

    private Integer userRemainingTime;//剩余时间

    private Integer userUseTime;

    private Integer date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userEndTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Date getUserStartTime() {
        return userStartTime;
    }

    public void setUserStartTime(Date userStartTime) {
        this.userStartTime = userStartTime;
    }

    public Date getUserEndTime() {
        return userEndTime;
    }

    public void setUserEndTime(Date userEndTime) {
        this.userEndTime = userEndTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOpusAllNumber() {
        return opusAllNumber;
    }

    public void setOpusAllNumber(Integer opusAllNumber) {
        this.opusAllNumber = opusAllNumber;
    }

    public Integer getOpusAllTime() {
        return opusAllTime;
    }

    public void setOpusAllTime(Integer opusAllTime) {
        this.opusAllTime = opusAllTime;
    }

    public Integer getUserRemainingTime() {
        return userRemainingTime;
    }

    public void setUserRemainingTime(Integer userRemainingTime) {
        this.userRemainingTime = userRemainingTime;
    }

    public Integer getUserUseTime() {
        return userUseTime;
    }

    public void setUserUseTime(Integer userUseTime) {
        this.userUseTime = userUseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}