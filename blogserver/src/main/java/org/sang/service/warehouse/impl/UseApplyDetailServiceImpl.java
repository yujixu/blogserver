package org.sang.service.warehouse.impl;

import org.sang.bean.warehouse.UseApplyDetail;
import org.sang.bean.warehouse.qo.UseApplyDetailQo;
import org.sang.mapper.warehouse.UseApplyDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UseApplyDetailServiceImpl {
    @Resource
    private UseApplyDetailMapper useApplyDetailMapper;


    public List<UseApplyDetailQo> getUseApplyDetailByPId(int pId) {
        return useApplyDetailMapper.getUseApplyDetail(pId);
    }
}
