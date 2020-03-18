package org.sang.controller.plant;

import org.sang.bean.blog.RespBean;
import org.sang.bean.plant.InPlant;
import org.sang.bean.plant.qo.InPlantQo;
import org.sang.service.plant.InPlantServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inPlant")
public class InPlantController {
    @Resource
    private InPlantServiceImpl inPlantService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllInPlant(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = inPlantService.getInPlantCount(keywords);
        List<InPlantQo> InPlantList = inPlantService.getAllInPlant(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("inPlant", InPlantList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<InPlantQo> getInPlant(){
        return inPlantService.getInPlant();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addInPlant(InPlant inPlant){
        int result = inPlantService.addInPlant(inPlant);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InPlantQo getInPlantById(@PathVariable int id){
        return inPlantService.getInPlantById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteInPlant(int[] ids){
        int result = inPlantService.deleteInPlant(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "计量单位被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateInPlant(InPlant inPlant){
        if(inPlantService.updateInPlant(inPlant)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
