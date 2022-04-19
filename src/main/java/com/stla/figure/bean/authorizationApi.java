package com.stla.figure.bean;

import java.io.Serializable;

public class authorizationApi implements Serializable {

    private static final long serialVersionUID = 9073653277241865840L;

    private String apiCode;//24h令牌

    private String apiToken;//静态密钥

    private String deviceCode;//机器码

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

}
