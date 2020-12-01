package com.agency.broker;

import java.util.List;

public class WrapperPolicy {

    private List<PolicyBean> clientPolicies;

    public List<PolicyBean> getClientPolicies() {
        return clientPolicies;
    }

    public void setClientPolicies(List<PolicyBean> clientPolicies) {
        this.clientPolicies = clientPolicies;
    }
}
