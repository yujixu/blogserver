package org.sang.service.plant;

import org.sang.bean.plant.PlantUnit;
import org.sang.mapper.plant.PlantUnitMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlantUnitServiceImpl {
    @Resource
    private PlantUnitMapper plantUnitMapper;

    public int getPlantUnitCount(String keywords) {
        return plantUnitMapper.getPlantUnitCount(keywords);
    }

    public List<PlantUnit> getAllPlantUnit(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return plantUnitMapper.getAllPlantUnit(page, count, start, keywords);
    }

    public List<PlantUnit> getPlantUnit() {
        return plantUnitMapper.getPlantUnit();
    }

    public int addPlantUnit(PlantUnit plantUnit) {
        return plantUnitMapper.addPlantUnit(plantUnit);
    }

    public PlantUnit getPlantUnitById(int id) {
        return plantUnitMapper.getPlantUnitById(id);
    }

    public int deletePlantUnit(int[] ids) {
        try{
            return plantUnitMapper.deletePlantUnit(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updatePlantUnit(PlantUnit plantUnit) {
        return plantUnitMapper.updatePlantUnit(plantUnit);
    }
}
