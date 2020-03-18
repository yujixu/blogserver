package org.sang.utils;

import org.sang.bean.warehouse.UseApply;
import org.sang.bean.warehouse.UseApplyDetail;

import java.util.List;

public class UseApplyAndDetails {
    private UseApply useApply;
    private List<UseApplyDetail> useApplyDetail;

    public UseApply getUseApply() {
        return useApply;
    }

    public void setUseApply(UseApply useApply) {
        this.useApply = useApply;
    }

    public List<UseApplyDetail> getUseApplyDetail() {
        return useApplyDetail;
    }

    public void setUseApplyDetail(List<UseApplyDetail> useApplyDetail) {
        this.useApplyDetail = useApplyDetail;
    }
}
