package com.agency.broker;

import java.math.BigDecimal;

public class AddOnBean {

    private Long addOnCode;
    private String addOnName;
    private BigDecimal addOnPrice;

    public Long getAddOnCode() {
        return addOnCode;
    }

    public void setAddOnCode(Long addOnCode) {
        this.addOnCode = addOnCode;
    }

    public String getAddOnName() {
        return addOnName;
    }

    public void setAddOnName(String addOnName) {
        this.addOnName = addOnName;
    }

    public BigDecimal getAddOnPrice() {
        return addOnPrice;
    }

    public void setAddOnPrice(BigDecimal addOnPrice) {
        this.addOnPrice = addOnPrice;
    }
}
