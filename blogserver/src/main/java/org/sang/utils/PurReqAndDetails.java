package org.sang.utils;

import org.sang.bean.purchase.PurReq;
import org.sang.bean.purchase.PurReqDetail;

import java.util.List;

public class PurReqAndDetails {
    private PurReq purReq;
    private List<PurReqDetail> purReqDetail;

    public PurReq getPurReq() {
        return purReq;
    }

    public void setPurReq(PurReq purReq) {
        this.purReq = purReq;
    }

    public List<PurReqDetail> getPurReqDetail() {

        return purReqDetail;
    }

    public void setPurReqDetail(List<PurReqDetail> purReqDetail) {

        this.purReqDetail = purReqDetail;
    }
}
