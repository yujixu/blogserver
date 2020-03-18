package org.sang.bean.business;

import java.util.List;

public class Industry {
    private String code;
    private String name;
    private boolean state;
    private String pId;
    private List<Industry> list;

    public List<Industry> getList() {
        return list;
    }

    public void setList(List<Industry> list) {
        this.list = list;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
