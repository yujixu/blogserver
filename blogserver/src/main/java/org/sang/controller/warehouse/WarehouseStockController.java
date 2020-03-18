package org.sang.controller.warehouse;

import org.sang.bean.blog.RespBean;
import org.sang.bean.warehouse.WarehouseStock;
import org.sang.bean.warehouse.qo.WarehouseStockQo;
import org.sang.service.warehouse.impl.WarehouseStockServiceImpl;
import org.sang.utils.WarehouseStockList;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class WarehouseStockController {
    @Resource
    private WarehouseStockServiceImpl warehouseStockService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addNewWarehouseStock(@RequestBody WarehouseStockList warehouseStockList){
        try{
            warehouseStockService.addNewWarehouseStock(warehouseStockList.getWarehouseStock(), warehouseStockList.getReceiptId());
            return new RespBean("success", "保存成功!");
        }
        catch (Exception e){
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getWarehouseStock(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = warehouseStockService.getWarehouseStockCount(keywords);
        List<WarehouseStockQo> warehouseReceiptList = warehouseStockService.getWarehouseStock(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("stock", warehouseReceiptList);
        return map;
    }

    @RequestMapping(value = "/getZero", method = RequestMethod.GET)
    public Map<String, Object> getZeroStock(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = warehouseStockService.getWarehouseStockCount(keywords);
        List<WarehouseStockQo> warehouseReceiptList = warehouseStockService.getZeroStock(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("stock", warehouseReceiptList);
        return map;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WarehouseStock getWarehouseStockById(@PathVariable int id){
        return warehouseStockService.getWarehouseStockById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateWarehouseStock(List<WarehouseStock> warehouseStockList){
        try{
            warehouseStockService.updateWarehouseStock(warehouseStockList);
            return new RespBean("success", "修改成功!");
        }catch (Exception e){
            return new RespBean("error", "修改失败!");
        }
    }

    @RequestMapping(value = "/getStock", method = RequestMethod.GET)
    public List<WarehouseStockQo> getStock(){
        return warehouseStockService.getStock();
    }
}
