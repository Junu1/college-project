package com.us.junu.project.model;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private final String key;

    Gender(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}




