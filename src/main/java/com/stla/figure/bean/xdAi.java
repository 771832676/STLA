package com.stla.figure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class xdAi implements Serializable {

    private static final long serialVersionUID = 6050329665657263148L;
    private Integer id;

    private String aiName;

    private String aiPhotoUrl;

    private String aiBodyUrl;

    private String aiVideoUrl;

    private String aiIntroduce;

    private Integer isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public String getAiBodyUrl() {
        return aiBodyUrl;
    }

    public void setAiBodyUrl(String aiBodyUrl) {
        this.aiBodyUrl = aiBodyUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAiName() {
        return aiName;
    }

    public void setAiName(String aiName) {
        this.aiName = aiName == null ? null : aiName.trim();
    }

    public String getAiPhotoUrl() {
        return aiPhotoUrl;
    }

    public void setAiPhotoUrl(String aiPhotoUrl) {
        this.aiPhotoUrl = aiPhotoUrl == null ? null : aiPhotoUrl.trim();
    }

    public String getAiVideoUrl() {
        return aiVideoUrl;
    }

    public void setAiVideoUrl(String aiVideoUrl) {
        this.aiVideoUrl = aiVideoUrl == null ? null : aiVideoUrl.trim();
    }

    public String getAiIntroduce() {
        return aiIntroduce;
    }

    public void setAiIntroduce(String aiIntroduce) {
        this.aiIntroduce = aiIntroduce == null ? null : aiIntroduce.trim();
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