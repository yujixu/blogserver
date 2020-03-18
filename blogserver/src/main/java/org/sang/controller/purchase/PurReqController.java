package org.sang.controller.purchase;

import org.sang.bean.blog.RespBean;
import org.sang.bean.purchase.PurReq;
import org.sang.service.purchase.PurReqService;
import org.sang.utils.PurReqAndDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purReq")
public class PurReqController {
    @Resource
    private PurReqService purReqService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addNewPurReq(@RequestBody PurReqAndDetails purReqAndDetails) {
        try{
            purReqService.addNewPurReq(purReqAndDetails.getPurReq(), purReqAndDetails.getPurReqDetail());
            return new RespBean("success", "保存成功!");
        }
        catch (Exception e){
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getPurReq(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = purReqService.getPurReqCount(keywords);
        List<PurReq> purReqList = purReqService.getPurReq(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("purReq", purReqList);
        return map;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PurReq getPurReqById(@PathVariable int id){
        return purReqService.getPurReqById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deletePurReq(int[] ids){
        if (purReqService.deletePurReq(ids) == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    public RespBean submitPurReq(int id){
        int result = purReqService.submitPurReq(id);
        if(result == 1){
            return new RespBean("success", "提交成功!");
        }else{
            return new RespBean("error", "提交失败!");
        }
    }

    @RequestMapping(value = "/addAndSubmit", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addAndSubmitPurReq(@RequestBody PurReqAndDetails purReqAndDetails){
        try{
            purReqService.addAndSubmitPurReq(purReqAndDetails.getPurReq(), purReqAndDetails.getPurReqDetail());
            return new RespBean("success", "提交成功!");
        }
        catch (Exception e){
            return new RespBean("error", "提交失败!");
        }
    }

    @RequestMapping(value = "/getExcute", method = RequestMethod.GET)
    public List<PurReq> getExcutePurReq(){
            return purReqService.getExcutePurReq();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public RespBean updatePurReq(@RequestBody PurReqAndDetails purReqAndDetails){
        try{
            purReqService.updatePurReq(purReqAndDetails.getPurReq(), purReqAndDetails.getPurReqDetail());
            return new RespBean("success", "修改成功!");
        }catch (Exception e){
            return new RespBean("error", "修改失败!");
        }
    }
}
