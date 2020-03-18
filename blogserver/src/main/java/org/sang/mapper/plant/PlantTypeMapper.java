package org.sang.mapper.plant;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.plant.PlantType;

import java.util.List;

@Mapper
public interface PlantTypeMapper {
    int getPlantTypeCount(@Param("keywords")String keywords);

    List<PlantType> getAllPlantType(@Param("page")Integer page, @Param("count")Integer count,
                                    @Param("start")Integer start, @Param("keywords")String keywords);

    List<PlantType> getPlantType();

    int deletePlantType(@Param("ids")int[] ids);

    PlantType getPlantTypeById(int id);

    int addPlantType(PlantType plantType);

    int updatePlantType(PlantType plantType);
}
