package org.sang.mapper.purchase;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.purchase.PurReqDetail;

import java.util.List;

@Mapper
public interface PurReqDetailMapper {
    List<PurReqDetail> getPurReqDetailByPId(int pId);

    void addPurReqDetails(List<PurReqDetail> purReqDetail);

    void deletePurReqDetails(@Param("pIds") int[] pIds);

    void submitPurReqDetails(int id);

    void check(List<PurReqDetail> purReqDetailList);

    List<PurReqDetail> getUnClosePurReqDetail(int pId);
}
