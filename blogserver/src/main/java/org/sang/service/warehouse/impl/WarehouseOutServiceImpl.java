package org.sang.service.warehouse.impl;

import org.sang.bean.warehouse.WarehouseOut;
import org.sang.bean.warehouse.WarehouseOutDetail;
import org.sang.bean.warehouse.WarehouseStock;
import org.sang.bean.warehouse.qo.WarehouseOutQo;
import org.sang.mapper.warehouse.UseApplyMapper;
import org.sang.mapper.warehouse.WarehouseOutDetailMapper;
import org.sang.mapper.warehouse.WarehouseOutMapper;
import org.sang.mapper.warehouse.WarehouseStockMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseOutServiceImpl {
    @Resource
    private WarehouseOutMapper warehouseOutMapper;
    @Resource
    private WarehouseOutDetailMapper warehouseOutDetailMapper;
    @Resource
    private UseApplyMapper useApplyMapper;
    @Resource
    private WarehouseStockMapper warehouseStockMapper;

    public void addNewWarehouseOut(WarehouseOut warehouseOut, List<WarehouseOutDetail> warehouseOutDetail) {
        warehouseOutMapper.addNewWarehouseOut(warehouseOut);
        if(warehouseOutDetail!=null){
            for(WarehouseOutDetail detail:warehouseOutDetail){
                detail.setpId(warehouseOut.getId());
            }
            warehouseOutDetailMapper.addWarehouseOutDetails(warehouseOutDetail);
        }
        useApplyMapper.outUseApply(warehouseOut.getUseApplyId());
        List<WarehouseStock> list = new ArrayList<>();
        for (WarehouseOutDetail detail:warehouseOutDetail) {
            WarehouseStock warehouseStock = warehouseStockMapper.getWarehouseStockById(detail.getStockId());
            warehouseStock.setStock(detail.getRest());
            warehouseStock.setLastReceiptDate(warehouseStock.getLastReceiptDate());
            warehouseStock.setLastOutDate(warehouseOut.getOutTime());
            list.add(warehouseStock);
        }
        warehouseStockMapper.updateWarehouseStock(list);
    }

    public int getWarehouseOutCount(String keywords) {
        return warehouseOutMapper.getWarehouseOutCount(keywords);
    }

    public List<WarehouseOutQo> getWarehouseOut(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return warehouseOutMapper.getWarehouseOut(page, count, start, keywords);
    }

    public WarehouseOutQo getWarehouseOutById(int id) {
        return warehouseOutMapper.getWarehouseOutById(id);
    }

    public int deleteWarehouseOut(int[] ids) {
        warehouseOutDetailMapper.deleteWarehouseOutDetails(ids);
        return warehouseOutMapper.deleteWarehouseOut(ids);
    }

    public void updateWarehouseOut(WarehouseOut warehouseOut) {
        warehouseOutMapper.updateWarehouseOut(warehouseOut);
    }
}
