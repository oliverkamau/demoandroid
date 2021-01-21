package com.agency.broker;

import java.util.List;

public class LoginResponse {

    private  String jwt;

    private  String username;

    private String type;

    private Long clientId;

    private List<String> roles;

    private String realName;

    private Integer expired;

    private Integer policies;

    private Integer quotes;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getPolicies() {
        return policies;
    }

    public void setPolicies(Integer policies) {
        this.policies = policies;
    }

    public Integer getQuotes() {
        return quotes;
    }

    public void setQuotes(Integer quotes) {
        this.quotes = quotes;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
