package org.sang.service.business;

import org.sang.bean.business.Room;
import org.sang.mapper.business.RoomMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomServiceImpl {
    @Resource
    private org.sang.mapper.business.RoomMapper RoomMapper;

    public int getRoomCount(String keywords) {
        return RoomMapper.getRoomCount(keywords);
    }

    public List<Room> getAllRoom(String keywords) {
        return RoomMapper.getAllRoom(keywords);
    }

    public List<Room> getRoom() {
        return RoomMapper.getRoom();
    }

    public int addRoom(Room room) {
        return RoomMapper.addRoom(room);
    }

    public Room getRoomById(int id) {
        return RoomMapper.getRoomById(id);
    }

    public int deleteRoom(int id) {
        try{
            return RoomMapper.deleteRoom(id);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateRoom(Room room) {
        return RoomMapper.updateRoom(room);
    }
}
