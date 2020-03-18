package org.sang.controller.plant;

import org.sang.bean.blog.RespBean;
import org.sang.bean.plant.PlantUnit;
import org.sang.service.plant.PlantUnitServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plantUnit")
public class PlantUnitController {
    @Resource
    private PlantUnitServiceImpl plantUnitService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllPlantUnit(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = plantUnitService.getPlantUnitCount(keywords);
        List<PlantUnit> PlantUnitList = plantUnitService.getAllPlantUnit(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("unit", PlantUnitList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PlantUnit> getPlantUnit(){
        return plantUnitService.getPlantUnit();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addPlantUnit(PlantUnit plantUnit){
        int result = plantUnitService.addPlantUnit(plantUnit);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlantUnit getPlantUnitById(@PathVariable int id){
        return plantUnitService.getPlantUnitById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deletePlantUnit(int[] ids){
        int result = plantUnitService.deletePlantUnit(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "植物单位被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updatePlantUnit(PlantUnit plantUnit){
        if(plantUnitService.updatePlantUnit(plantUnit)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
