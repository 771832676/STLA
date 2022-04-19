package com.stla.figure.bean;

import java.io.Serializable;

/**
 * 工作台-人像组件
 *
 * @author Michael Chow
 * @date   2021/08/20
 */
public class xdWorkbenchAi  implements Serializable {

    private static final long serialVersionUID = 7079961302686272634L;
    /**
     * 人像组件id
     */
    private Integer id;

    /**
     * 工作台id
     */
    private Integer elementId;

    /**
     * 虚拟人id
     */
    private Integer aiId;

    /**
     * 音色资源表主键
     */
    private Integer resourceId;

    /**
     * 音色资源表主键
     */
    private String resourceValue;

    /**
     * 人物位置 1:左侧 2:中间 3:右侧
     */
    private Integer site;

    /**
     * 音量值
     */
    private Double volume;

    /**
     * 语速
     */
    private Double speed;

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue;
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

    public Integer getAiId() {
        return aiId;
    }

    public void setAiId(Integer aiId) {
        this.aiId = aiId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}