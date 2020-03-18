package org.sang.service.business;

import org.sang.bean.business.Industry;
import org.sang.mapper.business.IndustryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndustryServiceImpl {
    @Resource
    IndustryMapper industryMapper;


    public List<Industry> getIndustry() {
        return industryMapper.getIndustry();
    }
}
