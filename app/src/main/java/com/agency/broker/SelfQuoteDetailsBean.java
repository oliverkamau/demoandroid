package com.agency.broker;

import java.util.List;

public class SelfQuoteDetailsBean {

    private Long selfQuoteId;
    private String cover;
    private String name;
    private String premium;
    private List<String> details;

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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
