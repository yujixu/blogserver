package org.sang.controller.purchase;

import org.sang.bean.purchase.PurContract;
import org.sang.bean.purchase.PurContractDetail;
import org.sang.bean.purchase.qo.PurContractDetailQo;
import org.sang.service.purchase.PurContractDetailService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/contractDetail")
public class PurContractDetailController {
    @Resource
    private PurContractDetailService purContractDetailService;

    @RequestMapping(value = "/{pId}", method = RequestMethod.GET)
    public List<PurContractDetailQo> getContractDetailByPId(@PathVariable int pId){
        return purContractDetailService.getContractDetailByPId(pId);
    }
}
