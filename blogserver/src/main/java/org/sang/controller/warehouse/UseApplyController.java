package org.sang.controller.warehouse;

import org.apache.commons.collections.map.HashedMap;
import org.sang.bean.blog.RespBean;
import org.sang.bean.warehouse.UseApply;
import org.sang.bean.warehouse.qo.UseApplyQo;
import org.sang.service.warehouse.impl.UseApplyServiceImpl;
import org.sang.utils.UseApplyAndDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/use")
public class UseApplyController {
    @Resource
    private UseApplyServiceImpl useApplyService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public RespBean addUseApply(@RequestBody UseApplyAndDetails useApplyAndDetails){
        try{
            useApplyService.addUseApply(useApplyAndDetails.getUseApply(), useApplyAndDetails.getUseApplyDetail());
            return new RespBean("success", "保存成功!");
        }catch (Exception e){
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getUseApply(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = useApplyService.getUseApplyCount(keywords);
        List<UseApplyQo> useApplyList = useApplyService.getUseApply(page, count, keywords);
        Map<String, Object> map = new HashedMap();
        map.put("totalCount", totalCount);
        map.put("useApply", useApplyList);
        return map;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UseApply getUseApplyById(@PathVariable int id){
        return useApplyService.getUseApplyById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteUseApply(int[] ids){
        if(useApplyService.deleteUseApply(ids)==ids.length){
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/check", method = RequestMethod.PUT)
    public RespBean checkUseApply(int id){
        int result = useApplyService.checkUseApply(id);
        if(result == 1){
            return new RespBean("success", "审核成功!");
        }
        return new RespBean("error", "审核失败!");
    }

    @RequestMapping(value = "/getCheck", method = RequestMethod.GET)
    public List<UseApply> getCheckUseApply(){
        return useApplyService.getCheckUseApply();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public RespBean updateUseApply(@RequestBody UseApplyAndDetails useApplyAndDetails){
        try{
            useApplyService.updateUseApply(useApplyAndDetails.getUseApply(), useApplyAndDetails.getUseApplyDetail());
            return new RespBean("success", "修改成功!");
        }catch (Exception e){
            return new RespBean("error", "修改失败!");
        }
    }

}
