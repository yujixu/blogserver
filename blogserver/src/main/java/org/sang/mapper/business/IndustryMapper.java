package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.business.Industry;

import java.util.List;

@Mapper
public interface IndustryMapper {

    List<Industry> getIndustry();
}
