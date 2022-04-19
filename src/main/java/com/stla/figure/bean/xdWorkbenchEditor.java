package com.stla.figure.bean;

import java.io.Serializable;

/**
 * 工作台-元素轴组件
 *
 * @author Michael Chow
 * @date   2021/08/20
 */
public class xdWorkbenchEditor  implements Serializable {

    private static final long serialVersionUID = -3763846282941224964L;
    /**
     * 元素轴id
     */
    private Integer id;

    /**
     * 工作台id
     */
    private Integer elementId;

    /**
     * 元素类型 1:文本 2:动作 3:视频 4:图片 5:延时  6:音频
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sorting;

    //0:文字配音 1视频原声
    private Integer isOrigin;

    /**
     * 是否开启字幕 0:关闭 1:开启
     */
    private Integer isCaption;

    /**
     * 文字内容
     */
    private String text;

    /**
     * 表情_资源表主键
     */
    private Integer expressionId;

    /**
     * 表情_资源表value值
     */
    private String expressionValue;

    /**
     * 单次动作_资源表主键逗号分割
     */
    private String actionId;

    /**
     * 单次动作_资源表value值逗号分割
     */
    private String actionValue;

    /**
     * 多次动作_资源表主键逗号分割
     */
    private String actionIds;

    /**
     * 多次动作_资源表value值逗号分割
     */
    private String actionValues;

    /**
     * 背景资源表主键
     */
    private Integer settingId;

    /**
     * 背景资源表url
     */
    private String settingUrl;

    private String settingName;

    /**
     * 视频_资源表主键
     */
    private Integer videoId;


    private String videoName;

    /**
     * 视频_资源表url
     */
    private String videoUrl;

    /**
     * 音频_资源表主键
     */
    private Integer audioId;

    /**
     * 音频_资源表url
     */
    private String audioUrl;

    private String audioName;

    /**
     * 图片_资源表主键
     */
    private Integer figId;

    /**
     * 图片_资源表url
     */
    private String figUrl;

    private String figName;

    /**
     * 延迟时间
     */
    private String delay;

    private Integer site;//视频图片附加参数-人物位置 1:左侧 2:中间 3:右侧
    private Integer tier;//视频图片附加参数-画中画层级 0主播上方 1主播下方
    private Integer isFull;//视频图片附加参数-0不全屏 1全屏
    private Integer xAxis;//视频图片附加参数-x轴
    private Integer yAxis;
    private Integer wAxis;
    private Integer hAxis;

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getFigName() {
        return figName;
    }

    public void setFigName(String figName) {
        this.figName = figName;
    }

    public Integer getIsOrigin() {
        return isOrigin;
    }

    public void setIsOrigin(Integer isOrigin) {
        this.isOrigin = isOrigin;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
    }

    public Integer getxAxis() {
        return xAxis;
    }

    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }

    public Integer getyAxis() {
        return yAxis;
    }

    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }

    public Integer getwAxis() {
        return wAxis;
    }

    public void setwAxis(Integer wAxis) {
        this.wAxis = wAxis;
    }

    public Integer gethAxis() {
        return hAxis;
    }

    public void sethAxis(Integer hAxis) {
        this.hAxis = hAxis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Integer getIsCaption() {
        return isCaption;
    }

    public void setIsCaption(Integer isCaption) {
        this.isCaption = isCaption;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(Integer expressionId) {
        this.expressionId = expressionId;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(String expressionValue) {
        this.expressionValue = expressionValue;
    }

    public String getActionIds() {
        return actionIds;
    }

    public void setActionIds(String actionIds) {
        this.actionIds = actionIds;
    }

    public String getActionValues() {
        return actionValues;
    }

    public void setActionValues(String actionValues) {
        this.actionValues = actionValues;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getSettingUrl() {
        return settingUrl;
    }

    public void setSettingUrl(String settingUrl) {
        this.settingUrl = settingUrl;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getAudioId() {
        return audioId;
    }

    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Integer getFigId() {
        return figId;
    }

    public void setFigId(Integer figId) {
        this.figId = figId;
    }

    public String getFigUrl() {
        return figUrl;
    }

    public void setFigUrl(String figUrl) {
        this.figUrl = figUrl;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}