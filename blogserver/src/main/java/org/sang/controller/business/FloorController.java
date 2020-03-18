package org.sang.controller.business;

import org.sang.bean.blog.RespBean;
import org.sang.bean.business.Floor;
import org.sang.service.business.FloorServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {
    @Resource
    private FloorServiceImpl FloorService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Floor> getAllFloor(String keywords){
        List<Floor> FloorList = FloorService.getAllFloor(keywords);
        return FloorList;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Floor> getFloor(){
        return FloorService.getFloor();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addFloor(Floor Floor){
        int result = FloorService.addFloor(Floor);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Floor getFloorById(@PathVariable int id){
        return FloorService.getFloorById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public RespBean deleteFloor(@PathVariable int id){
        int result = FloorService.deleteFloor(id);
        if (result == 1) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "楼层被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateFloor(Floor floor){
        if(FloorService.updateFloor(floor)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
