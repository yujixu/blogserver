package org.sang.controller.plant;

import org.sang.bean.blog.RespBean;
import org.sang.bean.plant.PlantType;
import org.sang.service.plant.PlantTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plantType")
public class PlantTypeController {
    @Resource
    private PlantTypeServiceImpl plantTypeService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllPlantType(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = plantTypeService.getPlantTypeCount(keywords);
        List<PlantType> PlantTypeList = plantTypeService.getAllPlantType(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("type", PlantTypeList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PlantType> getPlantType(){
        return plantTypeService.getPlantType();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addPlantType(PlantType plantType){
        int result = plantTypeService.addPlantType(plantType);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlantType getPlantTypeById(@PathVariable int id){
        return plantTypeService.getPlantTypeById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deletePlantType(int[] ids){
        int result = plantTypeService.deletePlantType(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "植物种类被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updatePlantType(PlantType plantType){
        if(plantTypeService.updatePlantType(plantType)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
