package org.sang.mapper.area;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.area.InArea;
import org.sang.bean.area.qo.InAreaQo;

import java.util.List;

@Mapper
public interface InAreaMapper {
    int getInAreaCount(@Param("keywords")String keywords);

    List<InAreaQo> getAllInArea(@Param("page")Integer page, @Param("count")Integer count,
                                  @Param("start")Integer start, @Param("keywords")String keywords);

    List<InAreaQo> getInArea();

    int deleteInArea(@Param("ids")int[] ids);

    InAreaQo getInAreaById(int id);

    int addInArea(InArea inArea);

    int updateInArea(InArea inArea);
}
