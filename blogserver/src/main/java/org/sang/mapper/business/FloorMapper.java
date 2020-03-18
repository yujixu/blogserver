package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.business.Floor;

import java.util.List;

@Mapper
public interface FloorMapper {
    int getFloorCount(@Param("keywords")String keywords);

    List<Floor> getAllFloor(@Param("keywords")String keywords);

    List<Floor> getFloor();

    int deleteFloor(int id);

    Floor getFloorById(int id);

    int addFloor(Floor floor);

    int updateFloor(Floor floor);

    List<Floor> getFloorByBuildingId(int id);

    void deleteByBuildingId(int id);
}
