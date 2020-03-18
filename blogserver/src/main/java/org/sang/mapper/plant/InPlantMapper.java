package org.sang.mapper.plant;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.plant.InPlant;
import org.sang.bean.plant.qo.InPlantQo;

import java.util.List;

@Mapper
public interface InPlantMapper {
    int getInPlantCount(@Param("keywords")String keywords);

    List<InPlantQo> getAllInPlant(@Param("page")Integer page, @Param("count")Integer count,
                                  @Param("start")Integer start, @Param("keywords")String keywords);

    List<InPlantQo> getInPlant();

    int deleteInPlant(@Param("ids")int[] ids);

    InPlantQo getInPlantById(int id);

    int addInPlant(InPlant inPlant);

    int updateInPlant(InPlant inPlant);
}
