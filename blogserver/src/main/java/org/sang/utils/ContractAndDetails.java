package org.sang.utils;

import org.sang.bean.purchase.PurContract;
import org.sang.bean.purchase.PurContractDetail;

import java.util.List;

public class ContractAndDetails {
    private PurContract purContract;
    private List<PurContractDetail> purContractDetail;

    public PurContract getPurContract() {
        return purContract;
    }

    public void setPurContract(PurContract purContract) {
        this.purContract = purContract;
    }

    public List<PurContractDetail> getPurContractDetail() {
        return purContractDetail;
    }

    public void setPurContractDetail(List<PurContractDetail> purContractDetail) {
        this.purContractDetail = purContractDetail;
    }
}
