package org.sang.utils;

import org.sang.bean.warehouse.WarehouseOut;
import org.sang.bean.warehouse.WarehouseOutDetail;

import java.util.List;

public class WarehouseOutAndDetails {
    private WarehouseOut warehouseOut;
    private List<WarehouseOutDetail> warehouseOutDetail;

    public WarehouseOut getWarehouseOut() {
        return warehouseOut;
    }

    public void setWarehouseOut(WarehouseOut warehouseOut) {
        this.warehouseOut = warehouseOut;
    }

    public List<WarehouseOutDetail> getWarehouseOutDetail() {
        return warehouseOutDetail;
    }

    public void setWarehouseOutDetail(List<WarehouseOutDetail> warehouseOutDetail) {
        this.warehouseOutDetail = warehouseOutDetail;
    }
}
