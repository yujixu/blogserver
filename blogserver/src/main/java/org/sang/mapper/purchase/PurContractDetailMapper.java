package org.sang.mapper.purchase;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.purchase.PurContractDetail;
import org.sang.bean.purchase.qo.PurContractDetailQo;

import java.util.List;

@Mapper
public interface PurContractDetailMapper {
    List<PurContractDetailQo> getContractDetailByPId(int pId);

    void addContractDetails(List<PurContractDetail> purReqDetail);

    void deleteContractDetails(@Param("pIds") int[] pIds);
}
