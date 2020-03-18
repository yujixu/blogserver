package org.sang.controller.business;

import org.sang.bean.blog.RespBean;
import org.sang.bean.business.TenancyAgreement;
import org.sang.service.business.TenancyAgreementServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agreement")
public class TenancyAgreementController {
    @Resource
    private TenancyAgreementServiceImpl agreementService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllTenancyAgreement(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = agreementService.getTenancyAgreementCount(keywords);
        List<TenancyAgreement> TenancyAgreementList = agreementService.getAllTenancyAgreement(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("agreement", TenancyAgreementList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<TenancyAgreement> getTenancyAgreement(){
        return agreementService.getTenancyAgreement();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addTenancyAgreement(TenancyAgreement tenancyAgreement){
        int result = agreementService.addTenancyAgreement(tenancyAgreement);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TenancyAgreement getTenancyAgreementById(@PathVariable int id){
        return agreementService.getTenancyAgreementById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteTenancyAgreement(int[] ids){
        int result = agreementService.deleteTenancyAgreement(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "协议被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateTenancyAgreement(TenancyAgreement tenancyAgreement){
        if(agreementService.updateTenancyAgreement(tenancyAgreement)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
