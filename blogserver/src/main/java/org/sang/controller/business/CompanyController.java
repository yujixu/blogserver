package org.sang.controller.business;

import org.sang.bean.blog.RespBean;
import org.sang.bean.business.Company;
import org.sang.bean.business.qo.CompanyQo;
import org.sang.service.business.CompanyServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyServiceImpl CompanyService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllCompany(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = CompanyService.getCompanyCount(keywords);
        List<CompanyQo> CompanyList = CompanyService.getAllCompany(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("company", CompanyList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<CompanyQo> getCompany(){
        return CompanyService.getCompany();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addCompany(Company company){
        int result = CompanyService.addCompany(company);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CompanyQo getCompanyById(@PathVariable int id){
        return CompanyService.getCompanyById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteCompany(int[] ids){
        int result = CompanyService.deleteCompany(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "计量单位被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateCompany(Company company){
        if(CompanyService.updateCompany(company)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
