package org.sang.mapper.warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.warehouse.WarehouseReceipt;
import org.sang.bean.warehouse.qo.WarehouseReceiptQo;

import java.util.List;

@Mapper
public interface WarehouseReceiptMapper {
    void addNewWarehouseReceipt(WarehouseReceipt warehouseReceipt);

    int getWarehouseReceiptCount(@Param("keywords")String keywords);

    List<WarehouseReceipt> getWarehouseReceipt(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    WarehouseReceiptQo getWarehouseReceiptById(int id);

    int deleteWarehouseReceiptById(@Param("ids")int[] ids);

    void updateWarehouseReceipt(WarehouseReceipt warehouseReceipt);

    int submitWarehouseReceipt(int id);

    List<WarehouseReceipt> getExcuteWarehouseReceipt();

    void close(int receiptId);
}
