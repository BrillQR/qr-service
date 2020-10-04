package com.brillqr.helper;

public enum Abbreviation {

	QR("QR"),
    USER("USR");

    private String abbreviation;

    Abbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String abbreviation() {
        return abbreviation;
    }
}
