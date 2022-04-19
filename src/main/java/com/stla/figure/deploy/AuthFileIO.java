package com.stla.figure.deploy;


import java.io.Serializable;

public class AuthFileIO implements Serializable {

    private String fileMd5;

    public AuthFileIO() {
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

}
