package org.sang.mapper.business;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.business.Bank;

import java.util.List;

@Mapper
public interface BankMapper {
    List<Bank> getBank();
}
