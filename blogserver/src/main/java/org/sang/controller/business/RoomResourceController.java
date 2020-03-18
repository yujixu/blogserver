package org.sang.controller.business;

import org.sang.bean.blog.RespBean;
import org.sang.bean.business.RoomResource;
import org.sang.bean.business.qo.RoomResourceQo;
import org.sang.service.business.RoomResourceServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roomRsrc")
public class RoomResourceController {
    @Resource
    private RoomResourceServiceImpl roomResourceService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllBaseUnit(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count,
                                              @RequestParam Integer parkId, @RequestParam Integer buildingId, @RequestParam Integer floorId, String keywords){
        int totalCount = roomResourceService.getRoomResourceCount(parkId, buildingId, floorId, keywords);
        List<RoomResourceQo> roomRsrcList = roomResourceService.getAllRoomResource(page, count, parkId, buildingId, floorId, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("roomRsrc", roomRsrcList);
        return map;
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<RoomResourceQo> getRoomResourceByParkAndFloor(@RequestParam Integer parkId, @RequestParam Integer buildingId, @RequestParam Integer floorId){
        return roomResourceService.getRoomResourceByParkAndFloor(parkId, buildingId, floorId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<RoomResourceQo> getRoomResource(){
        return roomResourceService.getRoomResource();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addRoomResource(RoomResource roomResource){
        int result = roomResourceService.addRoomResource(roomResource);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoomResourceQo getRoomResourceById(@PathVariable int id){
        return roomResourceService.getRoomResourceById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateRoomResource(RoomResource roomResource){
        if(roomResourceService.updateRoomResource(roomResource)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
