package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.blog.RespBean;
import org.sang.bean.warehouse.UseApply;
import org.sang.bean.warehouse.qo.UseApplyQo;

import java.util.List;

@Mapper
public interface UseApplyMapper {
    void addUseApply(UseApply useApply);

    int getUseApplyCount(@Param("keywords")String keywords);

    List<UseApplyQo> getUseApply(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    UseApply getUseApplyById(int id);

    int deleteUseApply(@Param("ids")int[] ids);

    List<UseApply> getCheckUseApply();

    int checkUseApply(int id);

    void updateUseApply(UseApply useApply);

    int outUseApply(int id);
}
