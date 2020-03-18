package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.warehouse.WarehouseReceiptDetail;
import org.sang.bean.warehouse.qo.WarehouseReceiptDetailQo;

import java.util.List;

@Mapper
public interface WarehouseReceiptDetailMapper {
    void addWarehouseReceiptDetails(List<WarehouseReceiptDetail> warehouseReceiptDetails);

    void deleteWarehouseReceiptDetails(@Param("pIds")int[] ids);

    List<WarehouseReceiptDetailQo> getWarehouseReceiptDetailByPId(int pId);

    void updateWarehouseReceiptDetail(List<WarehouseReceiptDetail> warehouseReceiptDetails);
}
