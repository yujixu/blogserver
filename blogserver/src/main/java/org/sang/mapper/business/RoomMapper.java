package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.business.Room;

import java.util.List;

@Mapper
public interface RoomMapper {
    int getRoomCount(@Param("keywords")String keywords);

    List<Room> getAllRoom(@Param("keywords")String keywords);

    List<Room> getRoom();

    int deleteRoom(int id);

    Room getRoomById(int id);

    int addRoom(Room room);

    int updateRoom(Room room);

    void deleteByFloorId(int[] ids);
}
