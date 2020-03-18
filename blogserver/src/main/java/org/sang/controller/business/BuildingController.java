package org.sang.controller.business;

import org.sang.bean.blog.RespBean;
import org.sang.bean.business.Building;
import org.sang.service.business.BuildingServiceImpl;
import org.sang.service.business.FloorServiceImpl;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/building")
public class BuildingController {
    @Resource
    private BuildingServiceImpl buildingService;
    @Resource
    private FloorServiceImpl floorService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Building> getAllBuilding(String keywords){
        List<Building> buildingList = buildingService.getAllBuilding(keywords);
        return buildingList;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Building> getBuilding(){
        return buildingService.getBuilding();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addBuilding(Building building){
        int result = buildingService.addBuilding(building);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Building getBuildingById(@PathVariable int id){
        return buildingService.getBuildingById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public RespBean deleteBuilding(@PathVariable int id){
        floorService.deleteByBuildingId(id);
        int result = buildingService.deleteBuilding(id);
        if (result == 1) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "楼栋被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateBuilding(Building building){
        if(buildingService.updateBuilding(building)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
