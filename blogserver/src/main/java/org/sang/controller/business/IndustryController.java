package org.sang.controller.business;

import org.sang.bean.business.Industry;
import org.sang.service.business.IndustryServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/industry")
public class IndustryController {
    @Resource
    private IndustryServiceImpl industryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Industry> getBaseUnit(){
        return industryService.getIndustry();
    }

}
