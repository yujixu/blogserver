package org.sang.service.plant;

import org.sang.bean.plant.PlantType;
import org.sang.mapper.plant.PlantTypeMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlantTypeServiceImpl {
    @Resource
    private PlantTypeMapper plantTypeMapper;

    public int getPlantTypeCount(String keywords) {
        return plantTypeMapper.getPlantTypeCount(keywords);
    }

    public List<PlantType> getAllPlantType(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return plantTypeMapper.getAllPlantType(page, count, start, keywords);
    }

    public List<PlantType> getPlantType() {
        return plantTypeMapper.getPlantType();
    }

    public int addPlantType(PlantType plantType) {
        return plantTypeMapper.addPlantType(plantType);
    }

    public PlantType getPlantTypeById(int id) {
        return plantTypeMapper.getPlantTypeById(id);
    }

    public int deletePlantType(int[] ids) {
        try{
            return plantTypeMapper.deletePlantType(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updatePlantType(PlantType plantType) {
        return plantTypeMapper.updatePlantType(plantType);
    }
}
