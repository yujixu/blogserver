package org.sang.mapper.area;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.area.OutArea;
import org.sang.bean.area.qo.OutAreaQo;

import java.util.List;

@Mapper
public interface OutAreaMapper {
    int getOutAreaCount(@Param("keywords")String keywords);

    List<OutAreaQo> getAllOutArea(@Param("page")Integer page, @Param("count")Integer count,
                                  @Param("start")Integer start, @Param("keywords")String keywords);

    List<OutAreaQo> getOutArea();

    int deleteOutArea(@Param("ids")int[] ids);

    OutAreaQo getOutAreaById(int id);

    int addOutArea(OutArea outArea);

    int updateOutArea(OutArea outArea);
}
