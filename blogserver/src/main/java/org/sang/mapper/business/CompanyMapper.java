package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.business.Company;
import org.sang.bean.business.qo.CompanyQo;

import java.util.List;

@Mapper
public interface CompanyMapper {int getCompanyCount(@Param("keywords")String keywords);

    List<CompanyQo> getAllCompany(@Param("page")Integer page, @Param("count")Integer count,
                                  @Param("start")Integer start, @Param("keywords")String keywords);

    List<CompanyQo> getCompany();

    int deleteCompany(@Param("ids")int[] ids);

    CompanyQo getCompanyById(int id);

    int addCompany(Company company);

    int updateCompany(Company company);
    
}
