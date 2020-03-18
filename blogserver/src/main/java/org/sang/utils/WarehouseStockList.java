package org.sang.utils;

import java.util.List;

public class WarehouseStockList {
    List<org.sang.bean.warehouse.WarehouseStock> warehouseStock;
    int receiptId;

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public List<org.sang.bean.warehouse.WarehouseStock> getWarehouseStock() {
        return warehouseStock;
    }

    public void setWarehouseStock(List<org.sang.bean.warehouse.WarehouseStock> warehouseStock) {
        this.warehouseStock = warehouseStock;
    }
}
