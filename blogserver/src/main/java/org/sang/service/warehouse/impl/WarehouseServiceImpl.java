package org.sang.service.warehouse.impl;

import org.sang.bean.warehouse.Warehouse;
import org.sang.mapper.warehouse.WarehouseMapper;
import org.sang.service.warehouse.WarehouseService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public int getWarehouseCount(String keywords) {
        return warehouseMapper.getWarehouseCount(keywords);
    }

    @Override
    public List<Warehouse> getAllWarehouse(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return warehouseMapper.getAllWarehouse(page, count, start, keywords);
    }

    @Override
    public List<Warehouse> getWarehouse() {
        return warehouseMapper.getWarehouse();
    }

    @Override
    public int addWarehouse(Warehouse warehouse) {
        return warehouseMapper.addWarehouse(warehouse);
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        return warehouseMapper.getWarehouseById(id);
    }

    @Override
    public int deleteWarehouse(int[] ids) {
        try{
            return warehouseMapper.deleteWarehouse(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    @Override
    public int updateWarehouse(Warehouse warehouse) {
        return warehouseMapper.updateWarehouse(warehouse);
    }
}
