package org.sang.bean.purchase;

import java.math.BigDecimal;

public class PurContractDetail {
    private int id;
    private int pId;
    private int materialId;
    private BigDecimal purOrder;
    private BigDecimal price;
    private BigDecimal amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getPurOrder() {
        return purOrder;
    }

    public void setPurOrder(BigDecimal purOrder) {
        this.purOrder = purOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
