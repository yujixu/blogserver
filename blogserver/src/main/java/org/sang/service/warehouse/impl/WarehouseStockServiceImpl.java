package org.sang.service.warehouse.impl;

import org.sang.bean.warehouse.WarehouseStock;
import org.sang.bean.warehouse.qo.WarehouseStockQo;
import org.sang.mapper.warehouse.WarehouseReceiptMapper;
import org.sang.mapper.warehouse.WarehouseStockMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseStockServiceImpl {
    @Resource
    private WarehouseStockMapper warehouseStockMapper;
    @Resource
    private WarehouseReceiptMapper warehouseReceiptMapper;

    public void addNewWarehouseStock(List<WarehouseStock> warehouseStockList, int receiptId) {
        List<WarehouseStock> toAddList = new ArrayList<>();
        List<WarehouseStock> toUpdateList = new ArrayList<>();
        for(WarehouseStock warehouseStock : warehouseStockList){
            WarehouseStock toUpdateWarehouseStock = warehouseStockMapper.getStockByIdAndPrice(warehouseStock.getMaterialId()
                    , warehouseStock.getWarehouseId(), warehouseStock.getPrice());
            if(toUpdateWarehouseStock!=null){
                toUpdateWarehouseStock.setStock(warehouseStock.getStock().add(toUpdateWarehouseStock.getStock()));
                toUpdateWarehouseStock.setLastReceiptDate(warehouseStock.getLastReceiptDate());
                toUpdateWarehouseStock.setLastOutDate(toUpdateWarehouseStock.getLastOutDate());
                toUpdateList.add(toUpdateWarehouseStock);
            }
            else{
                toAddList.add(warehouseStock);
            }
        }
        if(toAddList.size()!=0){
            warehouseStockMapper.addNewWarehouseStock(toAddList);
        }
        if(toUpdateList.size()!=0){
            warehouseStockMapper.updateWarehouseStock(toUpdateList);
        }
        warehouseReceiptMapper.close(receiptId);
    }

    public int getWarehouseStockCount(String keywords) {
        return warehouseStockMapper.getWarehouseStockCount(keywords);
    }

    public List<WarehouseStockQo> getWarehouseStock(Integer page, Integer count, String keywords) {
        int start = (page -1) * count;
        return warehouseStockMapper.getWarehouseStock(page, count, start, keywords);
    }

    public void updateWarehouseStock(List<WarehouseStock> warehouseStockList) {
        warehouseStockMapper.updateWarehouseStock(warehouseStockList);
    }

    public WarehouseStock getWarehouseStockById(int id) {
        return warehouseStockMapper.getWarehouseStockById(id);
    }

    public List<WarehouseStockQo> getStock() {
        return warehouseStockMapper.getStock();
    }

    public List<WarehouseStockQo> getZeroStock(Integer page, Integer count, String keywords) {
        int start = (page -1) * count;
        return warehouseStockMapper.getZeroStock(page, count, start, keywords);
    }
}
