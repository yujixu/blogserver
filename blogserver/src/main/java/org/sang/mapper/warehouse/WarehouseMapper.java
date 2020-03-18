package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.warehouse.Warehouse;

import java.util.List;

@Mapper
public interface WarehouseMapper {
    int getWarehouseCount(@Param("keywords")String keywords);

    List<Warehouse> getAllWarehouse(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    List<Warehouse> getWarehouse();

    int deleteWarehouse(@Param("ids")int[] ids);

    Warehouse getWarehouseById(int id);

    int addWarehouse(Warehouse baseUnit);

    int updateWarehouse(Warehouse baseUnit);
}
