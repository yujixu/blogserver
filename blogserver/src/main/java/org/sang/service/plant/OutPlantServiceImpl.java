package org.sang.service.plant;

import org.sang.bean.plant.OutPlant;
import org.sang.bean.plant.qo.OutPlantQo;
import org.sang.mapper.plant.OutPlantMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OutPlantServiceImpl {
    @Resource
    private org.sang.mapper.plant.OutPlantMapper OutPlantMapper;

    public int getOutPlantCount(String keywords) {
        return OutPlantMapper.getOutPlantCount(keywords);
    }

    public List<OutPlantQo> getAllOutPlant(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return OutPlantMapper.getAllOutPlant(page, count, start, keywords);
    }

    public List<OutPlantQo> getOutPlant() {
        return OutPlantMapper.getOutPlant();
    }

    public int addOutPlant(OutPlant outPlant) {
        return OutPlantMapper.addOutPlant(outPlant);
    }

    public OutPlantQo getOutPlantById(int id) {
        return OutPlantMapper.getOutPlantById(id);
    }

    public int deleteOutPlant(int[] ids) {
        try{
            return OutPlantMapper.deleteOutPlant(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateOutPlant(OutPlant outPlant) {
        return OutPlantMapper.updateOutPlant(outPlant);
    }
}
