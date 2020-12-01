package com.agency.broker;

public class PolicyDetailsBean {

    private Long policyId;
    private String policyNo;
    private String coverFrom;
    private String coverTo;
    private String renewalDate;
    private String status;
    private String balance;
    private String product;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getCoverFrom() {
        return coverFrom;
    }

    public void setCoverFrom(String coverFrom) {
        this.coverFrom = coverFrom;
    }

    public String getCoverTo() {
        return coverTo;
    }

    public void setCoverTo(String coverTo) {
        this.coverTo = coverTo;
    }

    public String getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
