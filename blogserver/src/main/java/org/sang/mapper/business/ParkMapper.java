package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.business.Park;

import java.util.List;

@Mapper
public interface ParkMapper {
    int getParkCount(@Param("keywords")String keywords);

    List<Park> getAllPark(@Param("keywords")String keywords);

    List<Park> getPark();

    Park getParkById(int id);

    int addPark(Park Park);

    int updatePark(Park Park);

    List<Park> getRsrcPark();
}
