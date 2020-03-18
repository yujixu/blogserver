package org.sang.controller.purchase;

import org.sang.bean.purchase.PurReq;
import org.sang.bean.purchase.PurReqDetail;
import org.sang.service.purchase.PurReqDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/purReqDetail")
public class PurReqDetailController {

    @Resource
    private PurReqDetailService purReqDetailService;

    @RequestMapping(value = "/{pId}", method = RequestMethod.GET)
    public List<PurReqDetail> getPurReqDetailByPId(@PathVariable int pId){
        return purReqDetailService.getPurReqDetailByPId(pId);
    }

    @RequestMapping(value = "/unClose/{pId}", method = RequestMethod.GET)
    public List<PurReqDetail> getUnClosePurReqDetail(@PathVariable int pId){
        return purReqDetailService.getUnClosePurReqDetail(pId);
    }
}
