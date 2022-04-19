package com.stla.figure.deploy;

import java.io.Serializable;

/**
 * Created by wt-templete-helper.
 */
public class ResourceFileResult implements Serializable{

    //@ApiModelProperty(value = "主键ID")
    private Integer id;
    //@ApiModelProperty(value = "文件标示")
    private String uuid;
    //@ApiModelProperty(value = "文件名")
    private String url;
    //@ApiModelProperty(value = "文件MD5")
    private String md5;
    //@ApiModelProperty(value = "文件大小")
    private Long lenght;
    //@ApiModelProperty(value = "文件名称")
    private String name;
    //@ApiModelProperty(value = "文件url")
    private String fileUrl;

    public ResourceFileResult() {
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMd5() {
        return this.md5;
    }
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    public Long getLenght() {
        return this.lenght;
    }
    public void setLenght(Long lenght) {
        this.lenght = lenght;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
