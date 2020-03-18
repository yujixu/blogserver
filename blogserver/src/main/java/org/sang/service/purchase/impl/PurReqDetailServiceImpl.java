package org.sang.service.purchase.impl;

import org.sang.bean.purchase.PurReqDetail;
import org.sang.mapper.purchase.PurReqDetailMapper;
import org.sang.service.purchase.PurReqDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurReqDetailServiceImpl implements PurReqDetailService {
    @Resource
    private PurReqDetailMapper purReqDetailMapper;

    @Override
    public List<PurReqDetail> getPurReqDetailByPId(int pId) {
        return purReqDetailMapper.getPurReqDetailByPId(pId);
    }

    @Override
    public List<PurReqDetail> getUnClosePurReqDetail(int pId) {
        return purReqDetailMapper.getUnClosePurReqDetail(pId);
    }

}
