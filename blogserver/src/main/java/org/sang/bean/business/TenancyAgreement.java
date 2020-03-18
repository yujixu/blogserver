package org.sang.bean.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.sang.bean.constant.AgreementState;
import org.sang.bean.constant.AgreementType;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TenancyAgreement {
    private int id;
    private AgreementType agreementType;
    private AgreementState agreementState;
    private int relatedAgreementId;
    private int COid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enterDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private BigDecimal deposit;
    private int paymentPeriod;
    private int payDay;
    private String remark;
    private int period;
    private BigDecimal rents;
    private float increasingRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AgreementType getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(AgreementType agreementType) {
        this.agreementType = agreementType;
    }

    public AgreementState getAgreementState() {
        return agreementState;
    }

    public void setAgreementState(AgreementState agreementState) {
        this.agreementState = agreementState;
    }

    public int getRelatedAgreementId() {
        return relatedAgreementId;
    }

    public void setRelatedAgreementId(int relatedAgreementId) {
        this.relatedAgreementId = relatedAgreementId;
    }

    public int getCOid() {
        return COid;
    }

    public void setCOid(int COid) {
        this.COid = COid;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(int paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public BigDecimal getRents() {
        return rents;
    }

    public void setRents(BigDecimal rents) {
        this.rents = rents;
    }

    public float getIncreasingRate() {
        return increasingRate;
    }

    public void setIncreasingRate(float increasingRate) {
        this.increasingRate = increasingRate;
    }
}


