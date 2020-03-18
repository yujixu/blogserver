package org.sang.controller.warehouse;

import org.sang.bean.warehouse.WarehouseReceiptDetail;
import org.sang.bean.warehouse.qo.WarehouseReceiptDetailQo;
import org.sang.service.warehouse.impl.WarehouseReceiptDetailServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/receiptDetail")
public class WarehouseReceiptDetailController {
    @Resource
    private WarehouseReceiptDetailServiceImpl warehouseReceiptDetailService;

    @RequestMapping(value = "/{pId}", method = RequestMethod.GET)
    public List<WarehouseReceiptDetailQo> getWarehouseReceiptDetailByPId(@PathVariable int pId){
        return warehouseReceiptDetailService.getWarehouseReceiptDetailByPId(pId);
    }
}
