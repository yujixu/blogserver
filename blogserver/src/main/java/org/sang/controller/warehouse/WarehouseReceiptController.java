package org.sang.controller.warehouse;

import org.sang.bean.blog.RespBean;
import org.sang.bean.warehouse.WarehouseReceipt;
import org.sang.bean.warehouse.qo.WarehouseReceiptQo;
import org.sang.service.warehouse.impl.WarehouseReceiptServiceImpl;
import org.sang.utils.WarehouseReceiptAndDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/receipt")
public class WarehouseReceiptController {
    @Resource
    private WarehouseReceiptServiceImpl warehouseReceiptService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addNewWarehouseReceipt(@RequestBody WarehouseReceiptAndDetails warehouseReceiptAndDetails) {
        try{
            warehouseReceiptService.addNewWarehouseReceipt(warehouseReceiptAndDetails.getWarehouseReceipt(), warehouseReceiptAndDetails.getWarehouseReceiptDetail());
            return new RespBean("success", "保存成功!");
        }
        catch (Exception e){
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getWarehouseReceipt(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = warehouseReceiptService.getWarehouseReceiptCount(keywords);
        List<WarehouseReceipt> warehouseReceiptList = warehouseReceiptService.getWarehouseReceipt(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("receipt", warehouseReceiptList);
        return map;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WarehouseReceiptQo getWarehouseReceiptById(@PathVariable int id){
        return warehouseReceiptService.getWarehouseReceiptById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteWarehouseReceipt(int[] ids){
        if (warehouseReceiptService.deleteWarehouseReceipt(ids) == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    public RespBean submitWarehouseReceipt(int id){
        int result = warehouseReceiptService.submitWarehouseReceipt(id);
        if(result == 1){
            return new RespBean("success", "提交成功!");
        }else{
            return new RespBean("error", "提交失败!");
        }
    }

    @RequestMapping(value = "/addAndSubmit", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addAndSubmitContract(@RequestBody WarehouseReceiptAndDetails warehouseReceiptAndDetails){
        try{
            warehouseReceiptService.addAndSubmitWarehouseReceipt(warehouseReceiptAndDetails.getWarehouseReceipt(), warehouseReceiptAndDetails.getWarehouseReceiptDetail());
            return new RespBean("success", "提交成功!");
        }
        catch (Exception e){
            return new RespBean("error", "提交失败!");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public RespBean updateWarehouseReceipt(@RequestBody WarehouseReceiptAndDetails warehouseReceiptAndDetails){
        try{
            warehouseReceiptService.updateWarehouseReceipt(warehouseReceiptAndDetails.getWarehouseReceipt(), warehouseReceiptAndDetails.getWarehouseReceiptDetail());
            return new RespBean("success", "修改成功!");
        }catch (Exception e){
            return new RespBean("error", "修改失败!");
        }
    }

    @RequestMapping(value = "/getExcute", method = RequestMethod.GET)
    public List<WarehouseReceipt> getExcuteWarehouseReceipt(){
        return warehouseReceiptService.getExcuteWarehouseReceipt();
    }
}
