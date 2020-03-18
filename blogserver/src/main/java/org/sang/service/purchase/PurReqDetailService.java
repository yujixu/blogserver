package org.sang.service.purchase;

import org.sang.bean.purchase.PurReqDetail;

import java.util.List;

public interface PurReqDetailService {
    List<PurReqDetail> getPurReqDetailByPId(int pId);

    List<PurReqDetail> getUnClosePurReqDetail(int pId);
}
