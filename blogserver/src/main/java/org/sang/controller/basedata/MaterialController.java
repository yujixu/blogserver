package org.sang.controller.basedata;

import org.sang.bean.blog.RespBean;
import org.sang.bean.basedata.Material;
import org.sang.service.basedata.MaterialService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Resource
    private MaterialService materialService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllMaterial(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = materialService.getMaterialCount(keywords);
        List<Material> materialList = materialService.getAllMaterial(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("material", materialList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Material> getMaterial(){
        return materialService.getMaterial();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addMaterial(Material material){
        int result = materialService.addMaterial(material);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Material getMaterialById(@PathVariable int id){
        return materialService.getMaterialById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteMaterial(int[] ids){
        int result = materialService.deleteMaterial(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "物料被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateMaterial(Material material){
        if(materialService.updateMaterial(material)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
