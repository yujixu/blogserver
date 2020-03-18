package org.sang.service.business;

import org.sang.bean.business.RoomResource;
import org.sang.bean.business.qo.RoomResourceQo;
import org.sang.mapper.business.RoomResourceMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomResourceServiceImpl {
    @Resource
    private RoomResourceMapper roomResourceMapper;

    public int getRoomResourceCount(Integer parkId, Integer buildingId, Integer floorId,String keywords) {
        return roomResourceMapper.getRoomResourceCount(parkId, buildingId, floorId, keywords);
    }

    public List<RoomResourceQo> getAllRoomResource(Integer page, Integer count,Integer parkId, Integer buildingId, Integer floorId, String keywords) {
        int start = (page - 1) * count;
        return roomResourceMapper.getAllRoomResource(page, count, start, parkId, buildingId, floorId, keywords);
    }

    public List<RoomResourceQo> getRoomResource() {
        return roomResourceMapper.getRoomResource();
    }

    public int addRoomResource(RoomResource RoomResource) {
        return roomResourceMapper.addRoomResource(RoomResource);
    }

    public RoomResourceQo getRoomResourceById(int id) {
        return roomResourceMapper.getRoomResourceById(id);
    }

    public int deleteRoomResource(int[] ids) {
        try{
            return roomResourceMapper.deleteRoomResource(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateRoomResource(RoomResource RoomResource) {
        return roomResourceMapper.updateRoomResource(RoomResource);
    }

    public List<RoomResourceQo> getRoomResourceByParkAndFloor(Integer parkId, Integer buildingId, Integer floorId) {
        return roomResourceMapper.getByParkAndFloor(parkId, buildingId, floorId);
    }
}
