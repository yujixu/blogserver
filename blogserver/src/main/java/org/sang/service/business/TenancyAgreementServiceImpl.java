package org.sang.service.business;

import org.sang.bean.business.TenancyAgreement;
import org.sang.mapper.business.TenancyAgreementMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TenancyAgreementServiceImpl {
    @Resource
    private TenancyAgreementMapper tenancyAgreementMapper;

    public int getTenancyAgreementCount(String keywords) {
        return tenancyAgreementMapper.getTenancyAgreementCount(keywords);
    }
    
    public List<TenancyAgreement> getAllTenancyAgreement(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return tenancyAgreementMapper.getAllTenancyAgreement(page, count, start, keywords);
    }
    
    public List<TenancyAgreement> getTenancyAgreement() {
        return tenancyAgreementMapper.getTenancyAgreement();
    }
    
    public int addTenancyAgreement(TenancyAgreement tenancyAgreement) {
        return tenancyAgreementMapper.addTenancyAgreement(tenancyAgreement);
    }

    public TenancyAgreement getTenancyAgreementById(int id) {
        return tenancyAgreementMapper.getTenancyAgreementById(id);
    }

    public int deleteTenancyAgreement(int[] ids) {
        try{
            return tenancyAgreementMapper.deleteTenancyAgreement(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateTenancyAgreement(TenancyAgreement tenancyAgreement) {
        return tenancyAgreementMapper.updateTenancyAgreement(tenancyAgreement);
    }
}
