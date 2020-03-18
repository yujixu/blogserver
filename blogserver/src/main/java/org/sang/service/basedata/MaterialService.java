package org.sang.service.basedata;

import org.sang.bean.basedata.Material;

import java.util.List;

public interface MaterialService {
    List<Material> getAllMaterial(Integer page, Integer count, String keywords);

    int getMaterialCount(String keywords);

    List<Material> getMaterial();

    int addMaterial(Material material);

    int deleteMaterial(int[] ids);

    Material getMaterialById(int id);

    int updateMaterial(Material material);
}
