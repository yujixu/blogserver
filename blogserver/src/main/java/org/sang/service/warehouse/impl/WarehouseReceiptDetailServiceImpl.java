package org.sang.service.warehouse.impl;

import org.sang.bean.warehouse.WarehouseReceiptDetail;
import org.sang.bean.warehouse.qo.WarehouseReceiptDetailQo;
import org.sang.mapper.warehouse.WarehouseReceiptDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseReceiptDetailServiceImpl {
    @Resource
    private WarehouseReceiptDetailMapper warehouseReceiptDetailMapper;

    public List<WarehouseReceiptDetailQo> getWarehouseReceiptDetailByPId(int pId) {
        return warehouseReceiptDetailMapper.getWarehouseReceiptDetailByPId(pId);
    }
}
