package org.sang.mapper.plant;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.plant.PlantUnit;

import java.util.List;

@Mapper
public interface PlantUnitMapper {
    int getPlantUnitCount(@Param("keywords")String keywords);

    List<PlantUnit> getAllPlantUnit(@Param("page")Integer page, @Param("count")Integer count,
                                    @Param("start")Integer start, @Param("keywords")String keywords);

    List<PlantUnit> getPlantUnit();

    int deletePlantUnit(@Param("ids")int[] ids);

    PlantUnit getPlantUnitById(int id);

    int addPlantUnit(PlantUnit plantUnit);

    int updatePlantUnit(PlantUnit plantUnit);
}
