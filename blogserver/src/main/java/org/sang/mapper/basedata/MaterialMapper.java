package org.sang.mapper.basedata;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.basedata.Material;

import java.util.List;

@Mapper
public interface MaterialMapper {

    List<Material> getAllMaterial(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    int getMaterialCount(@Param("keywords")String keywords);

    List<Material> getMaterial();

    int addMaterial(Material material);

    int deleteMaterial(@Param("ids")int[] ids);

    Material getMaterialById(int id);

    int updateMaterial(Material material);
}
