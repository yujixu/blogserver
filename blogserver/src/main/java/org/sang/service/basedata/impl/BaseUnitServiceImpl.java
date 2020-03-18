package org.sang.service.basedata.impl;

import org.sang.bean.basedata.BaseUnit;
import org.sang.mapper.basedata.BaseUnitMapper;
import org.sang.service.basedata.BaseUnitService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaseUnitServiceImpl implements BaseUnitService {
    @Resource
    private BaseUnitMapper baseUnitMapper;

    @Override
    public int getBaseUnitCount(String keywords) {
        return baseUnitMapper.getBaseUnitCount(keywords);
    }

    @Override
    public List<BaseUnit> getAllBaseUnit(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return baseUnitMapper.getAllBaseUnit(page, count, start, keywords);
    }

    @Override
    public List<BaseUnit> getBaseUnit() {
        return baseUnitMapper.getBaseUnit();
    }

    @Override
    public int addBaseUnit(BaseUnit baseUnit) {
        return baseUnitMapper.addBaseUnit(baseUnit);
    }

    @Override
    public BaseUnit getBaseUnitById(int id) {
        return baseUnitMapper.getBaseUnitById(id);
    }

    @Override
    public int deleteBaseUnit(int[] ids) {
        try{
            return baseUnitMapper.deleteBaseUnit(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    @Override
    public int updateBaseUnit(BaseUnit baseUnit) {
        return baseUnitMapper.updateBaseUnit(baseUnit);
    }
}
