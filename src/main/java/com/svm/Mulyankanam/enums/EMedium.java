package com.svm.Mulyankanam.enums;

public enum EMedium {
    ENGLISH("English"),
    GUJARATI("Gujarati");

    private final String medium;

    EMedium(String medium) {
        this.medium = medium;
    }

    public String getMedium() {
        return medium;
    }
}
