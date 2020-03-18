package org.sang.service.purchase;

import org.sang.bean.purchase.PurReq;
import org.sang.bean.purchase.PurReqDetail;

import java.util.List;

public interface PurReqService {
    void addNewPurReq(PurReq purReq, List<PurReqDetail> purReqDetails);

    int getPurReqCount(String keywords);

    List<PurReq> getPurReq(Integer page, Integer count, String keywords);

    PurReq getPurReqById(int id);

    int deletePurReq(int[] ids);

    int submitPurReq(int pId);

    void addAndSubmitPurReq(PurReq purReq, List<PurReqDetail> purReqDetail);

    List<PurReq> getExcutePurReq();

    void updatePurReq(PurReq purReq, List<PurReqDetail> purReqDetail);
}
