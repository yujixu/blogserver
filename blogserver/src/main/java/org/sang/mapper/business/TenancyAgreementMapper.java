package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.business.TenancyAgreement;

import java.util.List;

@Mapper
public interface TenancyAgreementMapper {
    int getTenancyAgreementCount(@Param("keywords")String keywords);

    List<TenancyAgreement> getAllTenancyAgreement(@Param("page")Integer page, @Param("count")Integer count,
                                                  @Param("start")Integer start, @Param("keywords")String keywords);

    List<TenancyAgreement> getTenancyAgreement();

    int deleteTenancyAgreement(@Param("ids")int[] ids);

    TenancyAgreement getTenancyAgreementById(int id);

    int addTenancyAgreement(TenancyAgreement tenancyAgreement);

    int updateTenancyAgreement(TenancyAgreement tenancyAgreement);
}
