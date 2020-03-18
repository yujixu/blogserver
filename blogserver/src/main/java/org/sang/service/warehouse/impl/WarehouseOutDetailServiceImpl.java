package org.sang.service.warehouse.impl;

import org.sang.bean.warehouse.qo.WarehouseOutDetailQo;
import org.sang.mapper.warehouse.WarehouseOutDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseOutDetailServiceImpl {
    @Resource
    private WarehouseOutDetailMapper warehouseOutDetailMapper;

    public List<WarehouseOutDetailQo> getWarehouseOutDetailByPId(int pId) {
        return warehouseOutDetailMapper.getWarehouseOutDetailByPId(pId);
    }

    public List<WarehouseOutDetailQo> getWarehouseOutDetailByStockId(int stockId) {
        return warehouseOutDetailMapper.getWarehouseOutDetailByStockId(stockId);
    }
}
