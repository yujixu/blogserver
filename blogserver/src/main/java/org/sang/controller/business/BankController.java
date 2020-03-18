package org.sang.controller.business;

import org.sang.bean.business.Bank;
import org.sang.service.business.BankServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Resource
    private BankServiceImpl BankService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Bank> getBaseUnit(){
        return BankService.getBank();
    }
}
