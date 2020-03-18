package org.sang.service.plant;

import org.sang.bean.plant.InPlant;
import org.sang.bean.plant.qo.InPlantQo;
import org.sang.mapper.plant.InPlantMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InPlantServiceImpl {
    @Resource
    private org.sang.mapper.plant.InPlantMapper InPlantMapper;

    public int getInPlantCount(String keywords) {
        return InPlantMapper.getInPlantCount(keywords);
    }

    public List<InPlantQo> getAllInPlant(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return InPlantMapper.getAllInPlant(page, count, start, keywords);
    }

    public List<InPlantQo> getInPlant() {
        return InPlantMapper.getInPlant();
    }

    public int addInPlant(InPlant inPlant) {
        return InPlantMapper.addInPlant(inPlant);
    }

    public InPlantQo getInPlantById(int id) {
        return InPlantMapper.getInPlantById(id);
    }

    public int deleteInPlant(int[] ids) {
        try{
            return InPlantMapper.deleteInPlant(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateInPlant(InPlant inPlant) {
        return InPlantMapper.updateInPlant(inPlant);
    }
}
