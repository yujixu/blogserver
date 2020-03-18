package org.sang.service.business;

import org.sang.bean.business.Building;
import org.sang.mapper.business.FloorMapper;
import org.sang.mapper.business.ParkMapper;
import org.sang.mapper.business.BuildingMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuildingServiceImpl {
    @Resource
    private BuildingMapper buildingMapper;

    public int getBuildingCount(String keywords) {
        return buildingMapper.getBuildingCount(keywords);
    }

    public List<Building> getAllBuilding(String keywords) {
        return buildingMapper.getAllBuilding(keywords);
    }

    public List<Building> getBuilding() {
        return buildingMapper.getBuilding();
    }

    public int addBuilding(Building Building) {
        return buildingMapper.addBuilding(Building);
    }

    public Building getBuildingById(int id) {
        return buildingMapper.getBuildingById(id);
    }

    public int deleteBuilding(int id) {
        try{
            return buildingMapper.deleteBuilding(id);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateBuilding(Building Building) {
        return buildingMapper.updateBuilding(Building);
    }

}
