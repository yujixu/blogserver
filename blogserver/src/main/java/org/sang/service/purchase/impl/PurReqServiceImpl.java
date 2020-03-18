package org.sang.service.purchase.impl;

import org.sang.bean.purchase.PurReq;
import org.sang.bean.purchase.PurReqDetail;
import org.sang.mapper.purchase.PurReqDetailMapper;
import org.sang.mapper.purchase.PurReqMapper;

import javax.annotation.Resource;

import org.sang.service.purchase.PurReqService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurReqServiceImpl implements PurReqService {
    @Resource
    PurReqMapper purReqMapper;
    @Resource
    PurReqDetailMapper purReqDetailMapper;

    @Override
    public void addNewPurReq(PurReq purReq, List<PurReqDetail> purReqDetails) {
        purReqMapper.addNewPurReq(purReq);
        if(purReqDetails != null){
            for(PurReqDetail purReqDetail:purReqDetails){
                purReqDetail.setpId(purReq.getId());
            }
            purReqDetailMapper.addPurReqDetails(purReqDetails);
        }
    }

    @Override
    public int getPurReqCount(String keywords) {
        return purReqMapper.getPurReqCount(keywords);
    }

    @Override
    public List<PurReq> getPurReq(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return purReqMapper.getPurReq(page, count, start,keywords);
    }

    @Override
    public PurReq getPurReqById(int id) {
        return purReqMapper.getPurReqById(id);
    }

    @Override
    public int deletePurReq(int[] ids) {
        purReqDetailMapper.deletePurReqDetails(ids);
        return purReqMapper.deletePurReqById(ids);
    }

    @Override
    public int submitPurReq(int id) {
        purReqDetailMapper.submitPurReqDetails(id);
        return purReqMapper.submitPurReq(id);
    }

    @Override
    public void addAndSubmitPurReq(PurReq purReq, List<PurReqDetail> purReqDetail) {
        addNewPurReq(purReq, purReqDetail);
        submitPurReq(purReq.getId());
    }

    @Override
    public List<PurReq> getExcutePurReq() {
        return purReqMapper.getExcutePurReq();
    }

    @Override
    public void updatePurReq(PurReq purReq, List<PurReqDetail> purReqDetail) {
        int[] pId = {purReq.getId()};
        purReqDetailMapper.deletePurReqDetails(pId);

        purReqMapper.updatePurReq(purReq);
        for(PurReqDetail detail:purReqDetail){
            detail.setpId(purReq.getId());
        }
        purReqDetailMapper.addPurReqDetails(purReqDetail);
    }
}
