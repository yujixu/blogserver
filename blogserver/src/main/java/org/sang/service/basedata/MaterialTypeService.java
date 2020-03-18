package org.sang.service.basedata;

import org.sang.bean.basedata.MaterialType;

import java.util.List;

public interface MaterialTypeService {

    List<MaterialType> getMaterialType();

    int getMaterialTypeCount(String keywords);

    List<MaterialType> getAllMaterialType(Integer page, Integer count, String keywords);

    int addMaterialType(MaterialType materialType);

    MaterialType getMaterialTypeById(int id);

    int deleteMaterialType(int[] ids);

    int updateMaterialType(MaterialType materialType);
}
