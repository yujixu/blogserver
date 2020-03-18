package org.sang.mapper.basedata;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.basedata.BaseUnit;

import java.util.List;

@Mapper
public interface BaseUnitMapper {

    int getBaseUnitCount(@Param("keywords")String keywords);

    List<BaseUnit> getAllBaseUnit(@Param("page")Integer page, @Param("count")Integer count,
                                  @Param("start")Integer start, @Param("keywords")String keywords);

    List<BaseUnit> getBaseUnit();

    int deleteBaseUnit(@Param("ids")int[] ids);

    BaseUnit getBaseUnitById(int id);

    int addBaseUnit(BaseUnit baseUnit);

    int updateBaseUnit(BaseUnit baseUnit);
}
