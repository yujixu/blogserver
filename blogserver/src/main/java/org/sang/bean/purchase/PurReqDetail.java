package org.sang.bean.purchase;

import org.sang.bean.constant.State;

import java.math.BigDecimal;

public class PurReqDetail {
    private int id;
    private int materialId;
    private int pId;
    private BigDecimal purQty;
    private BigDecimal purOrdered;
    private BigDecimal amount;
    private BigDecimal price;
    private String materialName;
    private String baseUnit;
    private String materialNumber;
    private String materialType;
    private String materialModel;
    private State states;

    public State getStates() {
        return states;
    }

    public void setStates(State states) {
        this.states = states;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public BigDecimal getPurQty() {
        return purQty;
    }

    public void setPurQty(BigDecimal purQty) {
        this.purQty = purQty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public BigDecimal getPurOrdered() {
        return purOrdered;
    }

    public void setPurOrdered(BigDecimal purOrdered) {
        this.purOrdered = purOrdered;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }
}
