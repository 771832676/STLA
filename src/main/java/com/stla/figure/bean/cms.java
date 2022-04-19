package com.stla.figure.bean;

import java.util.List;

public class cms {
    private Integer id;

    private String siteCode;

    private String siteName;

    private Integer type;

    private String title;

    private String content;

    private List<cmsOrFile> fileMd5;

    private List<cmsCmsOrFile> cmsCmsOrFileList;

    public List<cmsCmsOrFile> getCmsCmsOrFileList() {
        return cmsCmsOrFileList;
    }

    public void setCmsCmsOrFileList(List<cmsCmsOrFile> cmsCmsOrFileList) {
        this.cmsCmsOrFileList = cmsCmsOrFileList;
    }

    public List<cmsOrFile> getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(List<cmsOrFile> fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}