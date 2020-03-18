package org.sang.service.basedata.impl;

import org.sang.bean.basedata.MaterialType;
import org.sang.mapper.basedata.MaterialTypeMapper;
import org.sang.service.basedata.MaterialTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaterialTypeServiceImpl implements MaterialTypeService {
    @Resource
    private MaterialTypeMapper materialTypeMapper;

    @Override
    public List<MaterialType> getMaterialType() {
        return materialTypeMapper.getMaterialType();
    }

    @Override
    public int getMaterialTypeCount(String keywords) {
        return materialTypeMapper.getMaterialTypeCount(keywords);
    }

    @Override
    public List<MaterialType> getAllMaterialType(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return materialTypeMapper.getAllMaterialType(page, count, start, keywords);
    }

    @Override
    public int addMaterialType(MaterialType materialType) {
        return materialTypeMapper.addMaterialType(materialType);
    }

    @Override
    public MaterialType getMaterialTypeById(int id) {
        return materialTypeMapper.getMaterialTypeById(id);
    }

    @Override
    public int deleteMaterialType(int[] ids) {
        try{
            return materialTypeMapper.deleteMaterialType(ids);
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int updateMaterialType(MaterialType materialType) {
        return materialTypeMapper.updateMaterialType(materialType);
    }
}
