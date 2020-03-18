package org.sang.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class WarehouseStock {
    private int id;
    private int warehouseId;
    private int materialId;
    private BigDecimal stock;
    private BigDecimal price;
    private BigDecimal amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastReceiptDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date lastOutDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
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

    public Date getLastReceiptDate() {
        return lastReceiptDate;
    }

    public void setLastReceiptDate(Date lastReceiptDate) {
        this.lastReceiptDate = lastReceiptDate;
    }

    public Date getLastOutDate() {
        return lastOutDate;
    }

    public void setLastOutDate(Date lastOutDate) {
        this.lastOutDate = lastOutDate;
    }
}
