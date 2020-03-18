package org.sang.service.warehouse.impl;

import org.sang.bean.blog.RespBean;
import org.sang.bean.warehouse.UseApply;
import org.sang.bean.warehouse.UseApplyDetail;
import org.sang.bean.warehouse.qo.UseApplyQo;
import org.sang.mapper.warehouse.UseApplyDetailMapper;
import org.sang.mapper.warehouse.UseApplyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UseApplyServiceImpl {
    @Resource
    UseApplyMapper useApplyMapper;
    @Resource
    UseApplyDetailMapper useApplyDetailMapper;

    public void addUseApply(UseApply useApply, List<UseApplyDetail> useApplyDetails) {
        useApplyMapper.addUseApply(useApply);
        if(useApplyDetails!=null){
            for(UseApplyDetail detail:useApplyDetails){
                detail.setpId(useApply.getId());
            }
            useApplyDetailMapper.addUseApplyDetails(useApplyDetails);
        }
    }

    public int getUseApplyCount(String keywords) {
        return useApplyMapper.getUseApplyCount(keywords);
    }

    public List<UseApplyQo> getUseApply(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return useApplyMapper.getUseApply(page, count, start, keywords);
    }

    public UseApply getUseApplyById(int id) {
        return useApplyMapper.getUseApplyById(id);
    }

    public int deleteUseApply(int[] ids) {
        useApplyDetailMapper.deleteUseApplyDetails(ids);
        return useApplyMapper.deleteUseApply(ids);
    }

    public int checkUseApply(int id) {
        return useApplyMapper.checkUseApply(id);
    }

    public List<UseApply> getCheckUseApply() {
        return useApplyMapper.getCheckUseApply();
    }

    public void updateUseApply(UseApply useApply, List<UseApplyDetail> useApplyDetail) {
        int[] pId = {useApply.getId()};
        useApplyDetailMapper.deleteUseApplyDetails(pId);

        useApplyMapper.updateUseApply(useApply);
        for(UseApplyDetail detail:useApplyDetail){
            detail.setpId(useApply.getId());
        }
        useApplyDetailMapper.addUseApplyDetails(useApplyDetail);
    }
}
