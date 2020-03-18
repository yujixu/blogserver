package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.warehouse.WarehouseOutDetail;
import org.sang.bean.warehouse.qo.WarehouseOutDetailQo;

import java.util.List;

@Mapper
public interface WarehouseOutDetailMapper {

    List<WarehouseOutDetailQo> getWarehouseOutDetailByPId(int pId);

    void addWarehouseOutDetails(List<WarehouseOutDetail> warehouseOutDetail);

    void deleteWarehouseOutDetails(@Param("pIds")int[] ids);

    List<WarehouseOutDetailQo> getWarehouseOutDetailByStockId(int stockId);
}
