package org.sang.service.purchase;

import org.sang.bean.purchase.PurContractDetail;
import org.sang.bean.purchase.qo.PurContractDetailQo;

import java.util.List;

public interface PurContractDetailService {
    List<PurContractDetailQo> getContractDetailByPId(int pId);
}
