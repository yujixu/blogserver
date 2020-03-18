package org.sang.controller.area;

import org.sang.bean.area.InArea;
import org.sang.bean.area.qo.InAreaQo;
import org.sang.bean.blog.RespBean;
import org.sang.service.area.InAreaServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inArea")
public class InAreaController {
    @Resource
    private InAreaServiceImpl inAreaService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllInArea(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = inAreaService.getInAreaCount(keywords);
        List<InAreaQo> InAreaList = inAreaService.getAllInArea(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("inArea", InAreaList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<InAreaQo> getInArea(){
        return inAreaService.getInArea();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addInArea(InArea inArea){
        int result = inAreaService.addInArea(inArea);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InAreaQo getInAreaById(@PathVariable int id){
        return inAreaService.getInAreaById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteInArea(int[] ids){
        int result = inAreaService.deleteInArea(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "计量单位被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateInArea(InArea inArea){
        if(inAreaService.updateInArea(inArea)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
