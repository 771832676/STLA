package com.stla.figure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class xdUserStatisticsBean implements Serializable {


    private static final long serialVersionUID = 1993252094378846960L;

    private Integer id;

    private Integer opusTime;

    private String fileName;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpusTime() {
        return opusTime;
    }

    public void setOpusTime(Integer opusTime) {
        this.opusTime = opusTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}