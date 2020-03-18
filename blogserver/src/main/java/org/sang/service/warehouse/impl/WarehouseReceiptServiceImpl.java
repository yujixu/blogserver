package org.sang.service.warehouse.impl;

import org.sang.bean.purchase.PurContract;
import org.sang.bean.warehouse.WarehouseReceipt;
import org.sang.bean.warehouse.WarehouseReceiptDetail;
import org.sang.bean.warehouse.qo.WarehouseReceiptQo;
import org.sang.mapper.purchase.PurContractMapper;
import org.sang.mapper.warehouse.WarehouseReceiptDetailMapper;
import org.sang.mapper.warehouse.WarehouseReceiptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseReceiptServiceImpl {
    @Resource
    WarehouseReceiptMapper warehouseReceiptMapper;
    @Resource
    WarehouseReceiptDetailMapper warehouseReceiptDetailMapper;
    @Resource
    PurContractMapper purContractMapper;

    public void addNewWarehouseReceipt(WarehouseReceipt warehouseReceipt, List<WarehouseReceiptDetail> warehouseReceiptDetails) {
        warehouseReceiptMapper.addNewWarehouseReceipt(warehouseReceipt);
        if(warehouseReceiptDetails != null){
            for(WarehouseReceiptDetail warehouseReceiptDetail:warehouseReceiptDetails){
                warehouseReceiptDetail.setpId(warehouseReceipt.getId());
            }
            warehouseReceiptDetailMapper.addWarehouseReceiptDetails(warehouseReceiptDetails);
        }
        purContractMapper.receipt(warehouseReceipt.getContractId());
    }

    public int getWarehouseReceiptCount(String keywords) {
        return warehouseReceiptMapper.getWarehouseReceiptCount(keywords);
    }

    public List<WarehouseReceipt> getWarehouseReceipt(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return warehouseReceiptMapper.getWarehouseReceipt(page, count, start,keywords);
    }

    public WarehouseReceiptQo getWarehouseReceiptById(int id) {
        return warehouseReceiptMapper.getWarehouseReceiptById(id);
    }

    public int deleteWarehouseReceipt(int[] ids) {
        warehouseReceiptDetailMapper.deleteWarehouseReceiptDetails(ids);
        return warehouseReceiptMapper.deleteWarehouseReceiptById(ids);
    }

    public void updateWarehouseReceipt(WarehouseReceipt warehouseReceipt, List<WarehouseReceiptDetail> warehouseReceiptDetails) {
        warehouseReceiptMapper.updateWarehouseReceipt(warehouseReceipt);

        warehouseReceiptDetailMapper.updateWarehouseReceiptDetail(warehouseReceiptDetails);
    }

    public int submitWarehouseReceipt(int id) {
        return warehouseReceiptMapper.submitWarehouseReceipt(id);
    }

    public void addAndSubmitWarehouseReceipt(WarehouseReceipt warehouseReceipt, List<WarehouseReceiptDetail> warehouseReceiptDetails) {
        addNewWarehouseReceipt(warehouseReceipt, warehouseReceiptDetails);
        submitWarehouseReceipt(warehouseReceipt.getId());
    }

    public List<WarehouseReceipt> getExcuteWarehouseReceipt() {
        return warehouseReceiptMapper.getExcuteWarehouseReceipt();
    }
}
