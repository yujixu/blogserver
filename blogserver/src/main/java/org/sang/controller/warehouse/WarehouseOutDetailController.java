package org.sang.controller.warehouse;


import org.sang.bean.warehouse.WarehouseOut;
import org.sang.bean.warehouse.qo.WarehouseOutDetailQo;
import org.sang.service.warehouse.impl.WarehouseOutDetailServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/outDetail")
public class WarehouseOutDetailController {
    @Resource
    private WarehouseOutDetailServiceImpl warehouseOutDetailService;

    @RequestMapping(value = "/{pId}", method = RequestMethod.GET)
    public List<WarehouseOutDetailQo> getWarehouseOutDetailByPId(@PathVariable int pId){
        return warehouseOutDetailService.getWarehouseOutDetailByPId(pId);
    }

    @RequestMapping(value = "/hst/{stockId}", method = RequestMethod.GET)
    public List<WarehouseOutDetailQo> getWarehouseOutDetailByStockId(@PathVariable int stockId){
        return warehouseOutDetailService.getWarehouseOutDetailByStockId(stockId);
    }
}
