package org.sang.controller.business;

import org.sang.bean.business.Park;
import org.sang.bean.blog.RespBean;
import org.sang.service.business.BuildingServiceImpl;
import org.sang.service.business.ParkServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/park")
public class ParkController {
    @Resource
    private ParkServiceImpl parkService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Park> getAllPark(String keywords){
        List<Park> parkList = parkService.getAllPark(keywords);
        return parkList;
    }

    @RequestMapping(value = "/getRsrcAll", method = RequestMethod.GET)
    public List<Park> getRsrcPark(){
        List<Park> parkList = parkService.getRsrcPark();
        return parkList;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Park> getPark(){
        return parkService.getPark();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addPark(Park park){
        int result = parkService.addPark(park);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Park getParkById(@PathVariable int id){
        return parkService.getParkById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updatePark(Park park){
        if(parkService.updatePark(park)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
