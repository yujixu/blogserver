package org.sang.service.basedata;

import org.sang.bean.basedata.BaseUnit;

import java.util.List;

public interface BaseUnitService {
    int getBaseUnitCount(String keywords);

    List<BaseUnit> getAllBaseUnit(Integer page, Integer count, String keywords);

    List<BaseUnit> getBaseUnit();

    int addBaseUnit(BaseUnit baseUnit);

    BaseUnit getBaseUnitById(int id);

    int deleteBaseUnit(int[] ids);

    int updateBaseUnit(BaseUnit baseUnit);
}
