package org.sang.controller.warehouse;

import org.sang.bean.blog.RespBean;
import org.sang.bean.warehouse.Warehouse;
import org.sang.service.warehouse.WarehouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Resource
    private WarehouseService warehouseService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllWarehouse(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = warehouseService.getWarehouseCount(keywords);
        List<Warehouse> warehouseList = warehouseService.getAllWarehouse(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("warehouse", warehouseList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Warehouse> getWarehouse(){
        return warehouseService.getWarehouse();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addWarehouse(Warehouse warehouse){
        int result = warehouseService.addWarehouse(warehouse);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Warehouse getWarehouseById(@PathVariable int id){
        return warehouseService.getWarehouseById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteWarehouse(int[] ids){
        int result = warehouseService.deleteWarehouse(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "仓库被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateWarehouse(Warehouse warehouse){
        if(warehouseService.updateWarehouse(warehouse)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
