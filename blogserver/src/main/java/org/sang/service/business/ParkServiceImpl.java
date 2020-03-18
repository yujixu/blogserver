package org.sang.service.business;

import org.sang.bean.business.Park;
import org.sang.mapper.business.BuildingMapper;
import org.sang.mapper.business.ParkMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParkServiceImpl {
    @Resource
    private ParkMapper parkMapper;
    @Resource
    private BuildingMapper buildingMapper;

    public int getParkCount(String keywords) {
        return parkMapper.getParkCount(keywords);
    }

    public List<Park> getAllPark(String keywords) {
        return parkMapper.getAllPark(keywords);
    }

    public List<Park> getPark() {
        return parkMapper.getPark();
    }

    public int addPark(Park park) {
        return parkMapper.addPark(park);
    }

    public Park getParkById(int id) {
        return parkMapper.getParkById(id);
    }

    public int updatePark(Park park) {
        return parkMapper.updatePark(park);
    }

    public List<Park> getRsrcPark() {
        return parkMapper.getRsrcPark();
    }
}
