package org.sang.mapper.basedata;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.basedata.Supplier;

import java.util.List;

@Mapper
public interface SupplierMapper {
    int getSupplierCount(@Param("keywords")String keywords);

    List<Supplier> getAllSupplier(@Param("page")Integer page, @Param("count")Integer count,
                                  @Param("start")Integer start, @Param("keywords")String keywords);

    List<Supplier> getSupplier();

    int addSupplier(Supplier baseUnit);

    Supplier getSupplierById(int id);

    int deleteSupplier(@Param("ids")int[] ids);

    int updateSupplier(Supplier baseUnit);
}
