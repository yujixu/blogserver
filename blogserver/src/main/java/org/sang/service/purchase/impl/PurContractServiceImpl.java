package org.sang.service.purchase.impl;

import org.sang.bean.constant.State;
import org.sang.bean.purchase.PurContract;
import org.sang.bean.purchase.PurContractDetail;
import org.sang.bean.purchase.PurReq;
import org.sang.bean.purchase.PurReqDetail;
import org.sang.bean.purchase.qo.PurContractDetailQo;
import org.sang.bean.purchase.qo.PurContractQo;
import org.sang.mapper.purchase.PurContractDetailMapper;
import org.sang.mapper.purchase.PurContractMapper;
import org.sang.mapper.purchase.PurReqDetailMapper;
import org.sang.mapper.purchase.PurReqMapper;
import org.sang.service.purchase.PurContractService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PurContractServiceImpl implements PurContractService {
    @Resource
    PurContractMapper purContractMapper;
    @Resource
    PurContractDetailMapper purContractDetailMapper;
    @Resource
    PurReqMapper purReqMapper;
    @Resource
    PurReqDetailMapper purReqDetailMapper;

    @Override
    public void addNewContract(PurContract purContract, List<PurContractDetail> purContractDetails) {
        purContractMapper.addNewContract(purContract);
        if(purContractDetails != null){
            for(PurContractDetail purContractDetail:purContractDetails){
                purContractDetail.setpId(purContract.getId());
            }
            purContractDetailMapper.addContractDetails(purContractDetails);
        }
    }

    @Override
    public int getContractCount(String keywords) {
        return purContractMapper.getContractCount(keywords);
    }

    @Override
    public List<PurContract> getContract(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return purContractMapper.getContract(page, count, start,keywords);
    }

    @Override
    public PurContractQo getContractById(int id) {
        return purContractMapper.getContractById(id);
    }

    @Override
    public int deleteContract(int[] ids) {
        try{
            purContractDetailMapper.deleteContractDetails(ids);
            return purContractMapper.deleteContractById(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    @Override
    public int submitContract(int id) {
        return purContractMapper.submitContract(id);
    }

    @Override
    public void addAndSubmitContract(PurContract purContract, List<PurContractDetail> purContractDetails) {
        addNewContract(purContract, purContractDetails);
        submitContract(purContract.getId());
    }

    @Override
    public void updatePurContract(PurContract purContract, List<PurContractDetail> purContractDetail) {
        int[] pId = {purContract.getId()};
        purContractDetailMapper.deleteContractDetails(pId);

        purContractMapper.updatePurContract(purContract);
        for(PurContractDetail detail:purContractDetail){
            detail.setpId(purContract.getId());
        }
        purContractDetailMapper.addContractDetails(purContractDetail);
    }

    @Override
    public int checkPurContract(int id) {
        try {
            PurContractQo purContract = purContractMapper.getContractById(id);
            PurReq purReq = purReqMapper.getPurReqById(purContract.getPurreqId());
            List<PurReqDetail> purReqDetailList = purReqDetailMapper.getPurReqDetailByPId(purReq.getId());
            List<PurContractDetailQo> purContractDetailList = purContractDetailMapper.getContractDetailByPId(id);
            int temp = 0;
            for(int i=0; i<purContractDetailList.size(); i++){
                temp=0;
                PurContractDetailQo cDetail = purContractDetailList.get(i);
                for(int j=0; j<purReqDetailList.size(); j++){
                    PurReqDetail pDetail = purReqDetailList.get(j);
                    if(pDetail.getStates().equals(State.CLOSE)){
                        temp++;
                        continue;
                    }
                    if(cDetail.getMaterialId() != pDetail.getMaterialId()){
                        continue;
                    }
                    if(pDetail.getPurOrdered()==null){
                        pDetail.setPurOrdered(BigDecimal.ZERO);
                    }
                    if(cDetail.getPurOrder().add(pDetail.getPurOrdered()).compareTo(pDetail.getPurQty())==1){
                        return -1;
                    }
                    pDetail.setPurOrdered(cDetail.getPurOrder().add(pDetail.getPurOrdered()));
                    if(pDetail.getPurOrdered().compareTo(pDetail.getPurQty()) == 0){
                        temp++;
                        pDetail.setStates(State.CLOSE);
                    }
                    break;
                }
            }
            purReqDetailMapper.check(purReqDetailList);
            if(temp == purReqDetailList.size()){
                purReq.setState(State.CLOSE);
                purReqMapper.updatePurReq(purReq);
            }
            purContractMapper.check(id);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int recheckPurContract(int id) {
        try{
            PurContractQo purContract = purContractMapper.getContractById(id);
            PurReq purReq = purReqMapper.getPurReqById(purContract.getPurreqId());
            List<PurReqDetail> purReqDetailList = purReqDetailMapper.getPurReqDetailByPId(purReq.getId());
            List<PurContractDetailQo> purContractDetailList = purContractDetailMapper.getContractDetailByPId(id);

            for(int i=0; i<purContractDetailList.size(); i++){
                PurContractDetailQo cDetail = purContractDetailList.get(i);
                for(int j=0; j<purReqDetailList.size(); j++){
                    PurReqDetail pDetail = purReqDetailList.get(j);
                    if(pDetail.getMaterialId() != cDetail.getMaterialId()){
                        continue;
                    }
                    pDetail.setPurOrdered(pDetail.getPurOrdered().subtract(cDetail.getPurOrder()));
                    if(pDetail.getStates().equals(State.CLOSE)){
                        pDetail.setStates(State.EXCUTE);
                    }
                }
            }
            purReq.setState(State.EXCUTE);
            purReqDetailMapper.check(purReqDetailList);
            purReqMapper.updatePurReq(purReq);
            purContractMapper.recheck(id);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<PurContract> getClosePurContract() {
        return purContractMapper.getClosePurContract();
    }
}
