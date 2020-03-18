package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.warehouse.WarehouseStock;
import org.sang.bean.warehouse.qo.WarehouseStockQo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface WarehouseStockMapper {
    void addNewWarehouseStock(List<WarehouseStock> warehouseStockList);

    int getWarehouseStockCount(@Param("keywords")String keywords);

    List<WarehouseStockQo> getWarehouseStock(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    void updateWarehouseStock(List<WarehouseStock> warehouseStockList);

    WarehouseStock getWarehouseStockById(int id);

    List<WarehouseStockQo> getStock();

    List<WarehouseStockQo> getZeroStock(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    WarehouseStock getStockByIdAndPrice(@Param("materialId")int materialId, @Param("warehouseId")int warehouseId, @Param("price")BigDecimal price);
}
