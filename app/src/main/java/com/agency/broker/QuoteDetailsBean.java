package com.agency.broker;

public class QuoteDetailsBean {

    private Long quoteId;
    private String quoteNo;
    private String coverFrom;
    private String coverTo;
    private String expiry;
    private String status;
    private String ammount;
    private String product;

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
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

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
