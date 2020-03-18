package org.sang.service.basedata.impl;

import org.sang.bean.basedata.Material;
import org.sang.mapper.basedata.MaterialMapper;
import org.sang.service.basedata.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;

    @Override
    public List<Material> getAllMaterial(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return materialMapper.getAllMaterial(page, count, start, keywords);
    }

    @Override
    public int getMaterialCount(String keywords) {
        return materialMapper.getMaterialCount(keywords);
    }

    @Override
    public List<Material> getMaterial() {
        return materialMapper.getMaterial();
    }

    @Override
    public int addMaterial(Material material) {
        return materialMapper.addMaterial(material);
    }

    @Override
    public int deleteMaterial(int[] ids) {
        return materialMapper.deleteMaterial(ids);
    }

    @Override
    public Material getMaterialById(int id) {
        return materialMapper.getMaterialById(id);
    }

    @Override
    public int updateMaterial(Material material) {
        return materialMapper.updateMaterial(material);
    }
}
