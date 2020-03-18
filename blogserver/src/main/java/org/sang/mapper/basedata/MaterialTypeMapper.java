package org.sang.mapper.basedata;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.basedata.MaterialType;

import java.util.List;

@Mapper
public interface MaterialTypeMapper {
    List<MaterialType> getMaterialType();

    int getMaterialTypeCount(@Param("keywords")String keywords);

    List<MaterialType> getAllMaterialType(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    int addMaterialType(MaterialType materialType);

    MaterialType getMaterialTypeById(int id);

    int deleteMaterialType(@Param("ids")int[] ids);

    int updateMaterialType(MaterialType materialType);
}
