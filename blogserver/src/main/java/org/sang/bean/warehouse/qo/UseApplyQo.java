package org.sang.bean.warehouse.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.sang.bean.constant.ApplyReason;
import org.sang.bean.constant.AuditStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UseApplyQo {
    private int id;
    private String number;
    private String name;
    private int applicantId;
    private AuditStatus state;
    private ApplyReason reason;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date applyTime;

    private String applicant;

    private String isOut;

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public AuditStatus getState() {
        return state;
    }

    public void setState(AuditStatus state) {
        this.state = state;
    }

    public ApplyReason getReason() {
        return reason;
    }

    public void setReason(ApplyReason reason) {
        this.reason = reason;
    }
}
