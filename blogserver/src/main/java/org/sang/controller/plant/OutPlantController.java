package org.sang.controller.plant;

import org.sang.bean.blog.RespBean;
import org.sang.bean.plant.OutPlant;
import org.sang.bean.plant.qo.OutPlantQo;
import org.sang.service.plant.OutPlantServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/outPlant")
public class OutPlantController {
    @Resource
    private OutPlantServiceImpl outPlantService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllOutPlant(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = outPlantService.getOutPlantCount(keywords);
        List<OutPlantQo> OutPlantList = outPlantService.getAllOutPlant(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("outPlant", OutPlantList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<OutPlantQo> getOutPlant(){
        return outPlantService.getOutPlant();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addOutPlant(OutPlant outPlant){
        int result = outPlantService.addOutPlant(outPlant);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OutPlantQo getOutPlantById(@PathVariable int id){
        return outPlantService.getOutPlantById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteOutPlant(int[] ids){
        int result = outPlantService.deleteOutPlant(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == -1){
            return new RespBean("error", "计量单位被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateOutPlant(OutPlant outPlant){
        if(outPlantService.updateOutPlant(outPlant)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
