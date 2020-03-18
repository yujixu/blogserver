package org.sang.service.purchase;

import org.sang.bean.purchase.PurContract;
import org.sang.bean.purchase.PurContractDetail;
import org.sang.bean.purchase.qo.PurContractQo;

import java.util.List;

public interface PurContractService {
    void addNewContract(PurContract purContract, List<PurContractDetail> purContractDetails);

    int getContractCount(String keywords);

    List<PurContract> getContract(Integer page, Integer count, String keywords);

    PurContractQo getContractById(int id);

    int deleteContract(int[] ids);

    int submitContract(int id);

    void addAndSubmitContract(PurContract purContract, List<PurContractDetail> purContractDetails);

    void updatePurContract(PurContract purContract, List<PurContractDetail> purContractDetail);

    int checkPurContract(int id);

    int recheckPurContract(int id);

    List<PurContract> getClosePurContract();
}
