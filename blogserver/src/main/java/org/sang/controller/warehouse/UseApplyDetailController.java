package org.sang.controller.warehouse;

import org.sang.bean.warehouse.UseApplyDetail;
import org.sang.bean.warehouse.qo.UseApplyDetailQo;
import org.sang.service.warehouse.impl.UseApplyDetailServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/useDetail")
public class UseApplyDetailController {
    @Resource
    private UseApplyDetailServiceImpl useApplyDetailService;

    @RequestMapping(value = "/{pId}", method = RequestMethod.GET)
    public List<UseApplyDetailQo> getUseApplyDetailByPId(@PathVariable int pId){
        return useApplyDetailService.getUseApplyDetailByPId(pId);
    }

}
