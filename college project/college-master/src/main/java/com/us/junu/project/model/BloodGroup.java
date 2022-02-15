package com.us.junu.project.model;

public enum BloodGroup {
    AP("A+"),
    AN("A-"),
    BP("B+"),
    BN("B-"),
    ABP("AB+"),
    ABN("AB-"),
    OP("O+"),
    ON("O-"),
    PLASMA("Plasma");

    private final String key;

    BloodGroup(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

