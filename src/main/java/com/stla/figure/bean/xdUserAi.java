package com.stla.figure.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 作品表
 *
 * @author Michael Chow
 * @date   2021/08/20
 */
public class xdUserAi implements Serializable {

    private static final long serialVersionUID = -2179319278025667234L;
    /**
     * 主键
     */
    private Integer id;


    /**
     * 主键
     */
    private String ids;


    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 工作台id
     */
    private Integer workbench;

    /**
     * 作品名称
     */
    private String opusName;

    /**
     * 是否草稿 0:草稿 1:正式作品
     */
    private Integer isDraft;

    /**
     * 元素数量
     */
    private Integer elementSum;

    /**
     * 作品生成提交状态 0:未提交 1:已提交 2:生成完成
     */
    private Integer type;

    /**
     * 作品url
     */
    private String url;

    /**
     * 生成开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 生成结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date overTime;

    /**
     * 是否删除 0/未删除,1/已删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private int curPage=0;//当前页码
    private int pageCount = 10;//总页数

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
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

    public Integer getWorkbench() {
        return workbench;
    }

    public void setWorkbench(Integer workbench) {
        this.workbench = workbench;
    }

    public String getOpusName() {
        return opusName;
    }

    public void setOpusName(String opusName) {
        this.opusName = opusName;
    }

    public Integer getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Integer isDraft) {
        this.isDraft = isDraft;
    }

    public Integer getElementSum() {
        return elementSum;
    }

    public void setElementSum(Integer elementSum) {
        this.elementSum = elementSum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
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