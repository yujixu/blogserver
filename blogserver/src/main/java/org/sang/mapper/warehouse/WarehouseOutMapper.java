package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.warehouse.WarehouseOut;
import org.sang.bean.warehouse.qo.WarehouseOutQo;

import java.util.List;

@Mapper
public interface WarehouseOutMapper {

    void addNewWarehouseOut(WarehouseOut warehouseOut);

    int getWarehouseOutCount(@Param("keywords")String keywords);

    List<WarehouseOutQo> getWarehouseOut(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    WarehouseOutQo getWarehouseOutById(int id);

    int deleteWarehouseOut(@Param("ids")int[] ids);

    void updateWarehouseOut(WarehouseOut warehouseOut);
}
