package org.sang.service.business;

import org.sang.bean.business.Floor;
import org.sang.mapper.business.FloorMapper;
import org.sang.mapper.business.RoomMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FloorServiceImpl {
    @Resource
    private FloorMapper floorMapper;
    @Resource
    private RoomMapper roomMapper;

    public int getFloorCount(String keywords) {
        return floorMapper.getFloorCount(keywords);
    }

    public List<Floor> getAllFloor(String keywords) {
        return floorMapper.getAllFloor(keywords);
    }

    public List<Floor> getFloor() {
        return floorMapper.getFloor();
    }

    public int addFloor(Floor floor) {
        return floorMapper.addFloor(floor);
    }

    public Floor getFloorById(int id) {
        return floorMapper.getFloorById(id);
    }

    public int deleteFloor(int id) {
        try{
            int[] ids = {id};
            roomMapper.deleteByFloorId(ids);
            return floorMapper.deleteFloor(id);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateFloor(Floor floor) {
        return floorMapper.updateFloor(floor);
    }

    public void deleteByBuildingId(int id) {
        List<Floor> floorList = floorMapper.getFloorByBuildingId(id);
        int[] ids = new int[floorList.size()];
        for(int i = 0; i<floorList.size(); i++){
            ids[i] = floorList.get(i).getId();
        }
        roomMapper.deleteByFloorId(ids);
        floorMapper.deleteByBuildingId(id);
    }
}
