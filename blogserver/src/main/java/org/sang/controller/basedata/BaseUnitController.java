package org.sang.controller.basedata;

import org.sang.bean.blog.RespBean;
import org.sang.bean.basedata.BaseUnit;
import org.sang.service.basedata.BaseUnitService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/baseUnit")
public class BaseUnitController {
    @Resource
    private BaseUnitService baseUnitService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllBaseUnit(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = baseUnitService.getBaseUnitCount(keywords);
        List<BaseUnit> baseUnitList = baseUnitService.getAllBaseUnit(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("baseUnit", baseUnitList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<BaseUnit> getBaseUnit(){
        return baseUnitService.getBaseUnit();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addBaseUnit(BaseUnit baseUnit){
        int result = baseUnitService.addBaseUnit(baseUnit);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseUnit getBaseUnitById(@PathVariable int id){
        return baseUnitService.getBaseUnitById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteBaseUnit(int[] ids){
        int result = baseUnitService.deleteBaseUnit(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "计量单位被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateBaseUnit(BaseUnit baseUnit){
        if(baseUnitService.updateBaseUnit(baseUnit)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
