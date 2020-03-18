package org.sang.controller.basedata;

import org.sang.bean.blog.RespBean;
import org.sang.bean.basedata.MaterialType;
import org.sang.service.basedata.MaterialTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/materialType")
public class MaterialTypeController {
    @Resource
    private MaterialTypeService materialTypeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<MaterialType> getMaterialType(){
        return materialTypeService.getMaterialType();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllMaterial(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = materialTypeService.getMaterialTypeCount(keywords);
        List<MaterialType> materialTypeList = materialTypeService.getAllMaterialType(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("materialType", materialTypeList);
        return map;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addMaterialType(MaterialType materialType){
        int result = materialTypeService.addMaterialType(materialType);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MaterialType getMaterialTypeById(@PathVariable int id){
        return materialTypeService.getMaterialTypeById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteMaterialType(int[] ids){
        int result = materialTypeService.deleteMaterialType(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if (result == -1){
            return new RespBean("error", "有物料类型被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateMaterial(MaterialType materialType){
        if(materialTypeService.updateMaterialType(materialType)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
