package org.sang.mapper.plant;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.plant.OutPlant;
import org.sang.bean.plant.qo.OutPlantQo;

import java.util.List;

@Mapper
public interface OutPlantMapper {
    int getOutPlantCount(@Param("keywords")String keywords);

    List<OutPlantQo> getAllOutPlant(@Param("page")Integer page, @Param("count")Integer count,
                                    @Param("start")Integer start, @Param("keywords")String keywords);

    List<OutPlantQo> getOutPlant();

    int deleteOutPlant(@Param("ids")int[] ids);

    OutPlantQo getOutPlantById(int id);

    int addOutPlant(OutPlant outPlant);

    int updateOutPlant(OutPlant outPlant);
}
