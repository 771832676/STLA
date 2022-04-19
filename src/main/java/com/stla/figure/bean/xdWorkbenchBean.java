package com.stla.figure.bean;

import java.io.Serializable;
import java.util.List;

public class xdWorkbenchBean  implements Serializable {

    private static final long serialVersionUID = 1528184109693457950L;

    private Integer id;

    private xdWorkbench xdWorkbench;

    private List<xdWorkbenchAi> xdWorkbenchAi;

    private List<xdWorkbenchEditor> xdWorkbenchEditor;

    @Override
    public String toString() {
        return "xdWorkbenchBean{" +
                "id=" + id +
                ", xdWorkbench=" + xdWorkbench +
                ", xdWorkbenchAi=" + xdWorkbenchAi +
                ", xdWorkbenchEditor=" + xdWorkbenchEditor +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.stla.figure.bean.xdWorkbench getXdWorkbench() {
        return xdWorkbench;
    }

    public void setXdWorkbench(com.stla.figure.bean.xdWorkbench xdWorkbench) {
        this.xdWorkbench = xdWorkbench;
    }

    public List<com.stla.figure.bean.xdWorkbenchAi> getXdWorkbenchAi() {
        return xdWorkbenchAi;
    }

    public void setXdWorkbenchAi(List<com.stla.figure.bean.xdWorkbenchAi> xdWorkbenchAi) {
        this.xdWorkbenchAi = xdWorkbenchAi;
    }

    public List<com.stla.figure.bean.xdWorkbenchEditor> getXdWorkbenchEditor() {
        return xdWorkbenchEditor;
    }

    public void setXdWorkbenchEditor(List<com.stla.figure.bean.xdWorkbenchEditor> xdWorkbenchEditor) {
        this.xdWorkbenchEditor = xdWorkbenchEditor;
    }
}
