package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.warehouse.UseApplyDetail;
import org.sang.bean.warehouse.qo.UseApplyDetailQo;

import java.util.List;

@Mapper
public interface UseApplyDetailMapper {

    void addUseApplyDetails(List<UseApplyDetail> useApplyDetails);

    void deleteUseApplyDetails(@Param("pIds")int[] ids);

    List<UseApplyDetailQo> getUseApplyDetail(int pId);
}
