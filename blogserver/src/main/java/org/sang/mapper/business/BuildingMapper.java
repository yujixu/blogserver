package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.business.Building;

import java.util.List;

@Mapper
public interface BuildingMapper {
    int getBuildingCount(@Param("keywords")String keywords);

    List<Building> getAllBuilding(@Param("keywords")String keywords);

    List<Building> getBuilding();

    int deleteBuilding(int id);

    Building getBuildingById(int id);

    int addBuilding(Building building);

    int updateBuilding(Building building);
}
