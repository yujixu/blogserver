package org.sang.controller.area;

import org.sang.bean.area.OutArea;
import org.sang.bean.area.qo.OutAreaQo;
import org.sang.bean.blog.RespBean;
import org.sang.service.area.OutAreaServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/outArea")
public class OutAreaController {
    @Resource
    private OutAreaServiceImpl outAreaService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllOutArea(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = outAreaService.getOutAreaCount(keywords);
        List<OutAreaQo> outAreaList = outAreaService.getAllOutArea(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("outArea", outAreaList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<OutAreaQo> getOutArea(){
        return outAreaService.getOutArea();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addOutArea(OutArea outArea){
        int result = outAreaService.addOutArea(outArea);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OutAreaQo getOutAreaById(@PathVariable int id){
        return outAreaService.getOutAreaById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteOutArea(int[] ids){
        int result = outAreaService.deleteOutArea(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "计量单位被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateOutArea(OutArea outArea){
        if(outAreaService.updateOutArea(outArea)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
