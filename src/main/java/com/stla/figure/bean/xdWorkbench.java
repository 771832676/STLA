package com.stla.figure.bean;

import java.io.Serializable;

/**
 * 工作台表
 *
 * @author Michael Chow
 * @date   2021/08/20
 */
public class xdWorkbench  implements Serializable {

    private static final long serialVersionUID = 6755598289120496217L;
    /**
     * 工作台主键
     */
    private Integer id;

    /**
     * 屏幕比例
     */
    private String screen;

    /**
     * 背景资源表主键
     */
    private Integer settingId;

    /**
     * 背景url
     */
    private String settingUrl;

    /**
     * 人物位置 1:左侧 2:中间 3:右侧
     */
    private Integer site;

    /**
     * 主持人 1:1人主持 2:2人主持
     */
    private Integer hosts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
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

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    public Integer getHosts() {
        return hosts;
    }

    public void setHosts(Integer hosts) {
        this.hosts = hosts;
    }
}