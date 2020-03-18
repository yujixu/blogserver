package org.sang.utils;

import org.sang.bean.warehouse.WarehouseReceipt;
import org.sang.bean.warehouse.WarehouseReceiptDetail;

import java.util.List;

public class WarehouseReceiptAndDetails {
    private WarehouseReceipt warehouseReceipt;
    private List<WarehouseReceiptDetail> warehouseReceiptDetail;

    public WarehouseReceipt getWarehouseReceipt() {
        return warehouseReceipt;
    }

    public void setWarehouseReceipt(WarehouseReceipt warehouseReceipt) {
        this.warehouseReceipt = warehouseReceipt;
    }

    public List<WarehouseReceiptDetail> getWarehouseReceiptDetail() {
        return warehouseReceiptDetail;
    }

    public void setWarehouseReceiptDetail(List<WarehouseReceiptDetail> warehouseReceiptDetail) {
        this.warehouseReceiptDetail = warehouseReceiptDetail;
    }
}
