package org.sang.bean.business;

import java.util.List;

public class Park {
    private int id;
    private String name;
    private String number;
    private List<Building> list;

    public List<Building> getList() {
        return list;
    }

    public void setList(List<Building> list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
