package com.agency.broker;

import java.util.List;

public class SelfQuoteBean {

    private Long selfQuoteId;
    private String cover;
    private String name;
    private String premium;
    private String sumInsured;
    private List<AddOnBean> quoteAddOns;

    public List<AddOnBean> getQuoteAddOns() {
        return quoteAddOns;
    }

    public void setQuoteAddOns(List<AddOnBean> quoteAddOns) {
        this.quoteAddOns = quoteAddOns;
    }

    public String getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(String sumInsured) {
        this.sumInsured = sumInsured;
    }

    public Long getSelfQuoteId() {
        return selfQuoteId;
    }

    public void setSelfQuoteId(Long selfQuoteId) {
        this.selfQuoteId = selfQuoteId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }
}
