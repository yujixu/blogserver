package org.sang.controller.warehouse;

import org.sang.bean.blog.RespBean;
import org.sang.bean.warehouse.WarehouseOut;
import org.sang.bean.warehouse.qo.WarehouseOutQo;
import org.sang.service.warehouse.impl.WarehouseOutServiceImpl;
import org.sang.utils.WarehouseOutAndDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/out")
public class WarehouseOutController {
    @Resource
    private WarehouseOutServiceImpl warehouseOutService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addNewWarehouseOut(@RequestBody WarehouseOutAndDetails warehouseOutAndDetails) {
        try{
            warehouseOutService.addNewWarehouseOut(warehouseOutAndDetails.getWarehouseOut(), warehouseOutAndDetails.getWarehouseOutDetail());
            return new RespBean("success", "保存成功!");
        }
        catch (Exception e){
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getWarehouseOut(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = warehouseOutService.getWarehouseOutCount(keywords);
        List<WarehouseOutQo> warehouseOutList = warehouseOutService.getWarehouseOut(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("out", warehouseOutList);
        return map;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WarehouseOutQo getWarehouseOutById(@PathVariable int id){
        return warehouseOutService.getWarehouseOutById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteWarehouseOut(int[] ids){
        if (warehouseOutService.deleteWarehouseOut(ids) == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public RespBean updateWarehouseOut(@RequestBody WarehouseOutAndDetails warehouseOutAndDetails){
        try{
            warehouseOutService.updateWarehouseOut(warehouseOutAndDetails.getWarehouseOut());
            return new RespBean("success", "修改成功!");
        }catch (Exception e){
            return new RespBean("error", "修改失败!");
        }
    }

}
