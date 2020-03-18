package org.sang.bean.warehouse.qo;

import java.math.BigDecimal;

public class WarehouseReceiptDetailQo {
    private int id;
    private int warehouseId;
    private int materialId;
    private int pId;
    private BigDecimal purOrder;
    private BigDecimal receipt;
    private BigDecimal price;
    private BigDecimal amount;
    private String materialNumber;
    private String materialName;
    private String baseUnit;
    private String materialModel;
    private String materialType;

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
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

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

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

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public BigDecimal getPurOrder() {
        return purOrder;
    }

    public void setPurOrder(BigDecimal purOrder) {
        this.purOrder = purOrder;
    }

    public BigDecimal getReceipt() {
        return receipt;
    }

    public void setReceipt(BigDecimal receipt) {
        this.receipt = receipt;
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
