package org.sang.controller.basedata;

import org.sang.bean.basedata.Supplier;
import org.sang.bean.blog.RespBean;
import org.sang.service.basedata.impl.SupplierServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private SupplierServiceImpl supplierService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllSupplier(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = supplierService.getSupplierCount(keywords);
        List<Supplier> supplierList = supplierService.getAllSupplier(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("supplier", supplierList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Supplier> getSupplier(){
        return supplierService.getSupplier();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addSupplier(Supplier supplier){
        int result = supplierService.addSupplier(supplier);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Supplier getSupplierById(@PathVariable int id){
        return supplierService.getSupplierById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteSupplier(int[] ids){
        int result = supplierService.deleteSupplier(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "供应商被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateSupplier(Supplier supplier){
        if(supplierService.updateSupplier(supplier)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
