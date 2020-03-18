package org.sang.controller.business;

import org.sang.bean.blog.RespBean;
import org.sang.bean.business.Room;
import org.sang.service.business.RoomServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomServiceImpl RoomService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Room> getAllRoom(String keywords){
        List<Room> RoomList = RoomService.getAllRoom(keywords);
        return RoomList;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Room> getRoom(){
        return RoomService.getRoom();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addRoom(Room room){
        int result = RoomService.addRoom(room);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Room getRoomById(@PathVariable int id){
        return RoomService.getRoomById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public RespBean deleteRoom(@PathVariable int id){
        int result = RoomService.deleteRoom(id);
        if (result == 1) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "房间被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateRoom(Room room){
        if(RoomService.updateRoom(room)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
