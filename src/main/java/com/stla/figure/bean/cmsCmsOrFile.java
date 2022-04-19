package com.stla.figure.bean;

public class cmsCmsOrFile {
    private Integer id;

    private Integer cmsId;

    private Integer fileId;

    private String fileTitle;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmsId() {
        return cmsId;
    }

    public void setCmsId(Integer cmsId) {
        this.cmsId = cmsId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle == null ? null : fileTitle.trim();
    }
}