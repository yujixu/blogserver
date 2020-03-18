package org.sang.bean.warehouse;

import java.math.BigDecimal;

public class WarehouseOutDetail {
    private int id;
    private int stockId;
    private int useApplyDetailId;
    private int pId;
    private BigDecimal rest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getUseApplyDetailId() {
        return useApplyDetailId;
    }

    public void setUseApplyDetailId(int useApplyDetailId) {
        this.useApplyDetailId = useApplyDetailId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public BigDecimal getRest() {
        return rest;
    }

    public void setRest(BigDecimal rest) {
        this.rest = rest;
    }
}
