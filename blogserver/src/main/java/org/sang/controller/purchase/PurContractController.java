package org.sang.controller.purchase;

import org.sang.bean.blog.RespBean;
import org.sang.bean.purchase.PurContract;
import org.sang.bean.purchase.qo.PurContractQo;
import org.sang.service.purchase.PurContractService;
import org.sang.utils.ContractAndDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contract")
public class PurContractController {
    @Resource
    private PurContractService purContractService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addNewContract(@RequestBody ContractAndDetails contractAndDetails) {
        try{
            purContractService.addNewContract(contractAndDetails.getPurContract(), contractAndDetails.getPurContractDetail());
            return new RespBean("success", "保存成功!");
        }
        catch (Exception e){
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getContract(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = purContractService.getContractCount(keywords);
        List<PurContract> purContractList = purContractService.getContract(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("contract", purContractList);
        return map;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PurContractQo getContractById(@PathVariable int id){
        return purContractService.getContractById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteContract(int[] ids){
        int result = purContractService.deleteContract(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "合同已生成入库单,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    public RespBean submitContract(int id){
        int result = purContractService.submitContract(id);
        if(result == 1){
            return new RespBean("success", "提交成功!");
        }else{
            return new RespBean("error", "提交失败!");
        }
    }

    @RequestMapping(value = "/addAndSubmit", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addAndSubmitContract(@RequestBody ContractAndDetails contractAndDetails){
        try{
            purContractService.addAndSubmitContract(contractAndDetails.getPurContract(), contractAndDetails.getPurContractDetail());
            return new RespBean("success", "提交成功!");
        }
        catch (Exception e){
            return new RespBean("error", "提交失败!");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public RespBean updatePurContract(@RequestBody ContractAndDetails contractAndDetails){
        try{
            purContractService.updatePurContract(contractAndDetails.getPurContract(), contractAndDetails.getPurContractDetail());
            return new RespBean("success", "修改成功!");
        }catch (Exception e){
            return new RespBean("error", "修改失败!");
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.PUT)
    public RespBean checkPurContract(int id){
        int result = purContractService.checkPurContract(id);
        if(result == 1){
            return new RespBean("success", "审核成功!");
        }
        if(result == -1){
            return new RespBean("error", "订购数量超出申请数量, 审核失败!");
        }
        return new RespBean("error", "审核失败!");
    }

    @RequestMapping(value = "/recheck", method = RequestMethod.PUT)
    public RespBean recheckPurContract(int id){
        int result = purContractService.recheckPurContract(id);
        if(result == 1){
            return new RespBean("success", "反审核成功!");
        }
        return new RespBean("error", "反审核失败!");
    }

    @RequestMapping(value = "/getClose", method = RequestMethod.GET)
    public List<PurContract> getClosePurContract(){
        return purContractService.getClosePurContract();
    }
}
