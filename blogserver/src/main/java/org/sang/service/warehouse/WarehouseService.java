package org.sang.service.warehouse;

import org.sang.bean.warehouse.Warehouse;

import java.util.List;

public interface WarehouseService {
    int getWarehouseCount(String keywords);

    List<Warehouse> getAllWarehouse(Integer page, Integer count, String keywords);

    List<Warehouse> getWarehouse();

    int addWarehouse(Warehouse baseUnit);

    Warehouse getWarehouseById(int id);

    int deleteWarehouse(int[] ids);

    int updateWarehouse(Warehouse baseUnit);
}
