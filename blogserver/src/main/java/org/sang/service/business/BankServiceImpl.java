package org.sang.service.business;

import org.sang.bean.business.Bank;
import org.sang.mapper.business.BankMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BankServiceImpl {
    @Resource
    BankMapper BankMapper;


    public List<Bank> getBank() {
        return BankMapper.getBank();
    }
}
