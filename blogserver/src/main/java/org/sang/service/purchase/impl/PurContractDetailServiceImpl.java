package org.sang.service.purchase.impl;

import org.sang.bean.purchase.PurContractDetail;
import org.sang.bean.purchase.qo.PurContractDetailQo;
import org.sang.mapper.purchase.PurContractDetailMapper;
import org.sang.service.purchase.PurContractDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurContractDetailServiceImpl implements PurContractDetailService {

    @Resource
    private PurContractDetailMapper purContractDetailMapper;
    @Override
    public List<PurContractDetailQo> getContractDetailByPId(int pId) {
        return purContractDetailMapper.getContractDetailByPId(pId);
    }


}
