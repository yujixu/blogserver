package org.sang.service.business;

import org.sang.bean.business.Company;
import org.sang.bean.business.qo.CompanyQo;
import org.sang.mapper.business.CompanyMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl {
    @Resource
    private CompanyMapper CompanyMapper;

    public int getCompanyCount(String keywords) {
        return CompanyMapper.getCompanyCount(keywords);
    }

    public List<CompanyQo> getAllCompany(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return CompanyMapper.getAllCompany(page, count, start, keywords);
    }

    public List<CompanyQo> getCompany() {
        return CompanyMapper.getCompany();
    }

    public int addCompany(Company company) {
        return CompanyMapper.addCompany(company);
    }

    public CompanyQo getCompanyById(int id) {
        return CompanyMapper.getCompanyById(id);
    }

    public int deleteCompany(int[] ids) {
        try{
            return CompanyMapper.deleteCompany(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateCompany(Company company) {
        return CompanyMapper.updateCompany(company);
    }
}
