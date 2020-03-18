package org.sang.bean.plant.qo;

import java.math.BigDecimal;

public class InPlantQo {
    private int id;
    private int areaId;
    private String name;
    private int plantTypeId;
    private BigDecimal age;
    private BigDecimal number;
    private int plantUnitId;
    private String areaName;
    private String typeName;
    private String unitName;
    private String habit;

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getPlantTypeId() {
        return plantTypeId;
    }

    public void setPlantTypeId(int plantTypeId) {
        this.plantTypeId = plantTypeId;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public int getPlantUnitId() {
        return plantUnitId;
    }

    public void setPlantUnitId(int plantUnitId) {
        this.plantUnitId = plantUnitId;
    }
}
