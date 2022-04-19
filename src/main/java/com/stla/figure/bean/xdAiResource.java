package com.stla.figure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class xdAiResource implements Serializable {


    private static final long serialVersionUID = -2029832638082265439L;
    private Integer id;

    private Integer aiId;

    private Integer aiType;

    private String aiName;

    private String aiUrl;

    private String aiValue;

    private Integer isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAiId() {
        return aiId;
    }

    public void setAiId(Integer aiId) {
        this.aiId = aiId;
    }

    public Integer getAiType() {
        return aiType;
    }

    public void setAiType(Integer aiType) {
        this.aiType = aiType;
    }

    public String getAiName() {
        return aiName;
    }

    public void setAiName(String aiName) {
        this.aiName = aiName;
    }

    public String getAiUrl() {
        return aiUrl;
    }

    public void setAiUrl(String aiUrl) {
        this.aiUrl = aiUrl;
    }

    public String getAiValue() {
        return aiValue;
    }

    public void setAiValue(String aiValue) {
        this.aiValue = aiValue;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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