package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.business.RoomResource;
import org.sang.bean.business.qo.RoomResourceQo;

import java.util.List;

@Mapper
public interface RoomResourceMapper {
    int getRoomResourceCount(@Param("parkId")Integer parkId,@Param("buildingId")Integer buildingId,
                             @Param("floorId")Integer floorId, @Param("keywords")String keywords);

    List<RoomResourceQo> getAllRoomResource(@Param("page")Integer page, @Param("count")Integer count,
                                            @Param("start")Integer start, @Param("parkId")Integer parkId,
                                            @Param("buildingId")Integer buildingId,@Param("floorId")Integer floorId,
                                            @Param("keywords")String keywords);

    List<RoomResourceQo> getRoomResource();

    int deleteRoomResource(@Param("ids")int[] ids);

    RoomResourceQo getRoomResourceById(int id);

    int addRoomResource(RoomResource RoomResource);

    int updateRoomResource(RoomResource RoomResource);

    List<RoomResourceQo> getByParkAndFloor(Integer parkId, Integer buildingId, Integer floorId);
}
