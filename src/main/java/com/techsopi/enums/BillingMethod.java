package com.techsopi.enums;

public enum BillingMethod {
    BILLABLE("billable"), NONBILLABLE("nonbillable");

    private String value;

    private BillingMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}