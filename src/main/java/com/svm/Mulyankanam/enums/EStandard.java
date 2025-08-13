package com.svm.Mulyankanam.enums;

public enum EStandard{
    // Enum constants for each standard
    STD_1("1"),
    STD_2("2"),
    STD_3("3"),
    STD_4("4"),
    STD_5("5"),
    STD_6("6"),
    STD_7("7"),
    STD_8("8"),
    STD_9("9"),
    STD_10("10"),
    STD_11_COMMERCE("11_COM"),
    STD_11_SCIENCE_A("11_SCI_A"),
    STD_11_SCIENCE_B("11_SCI_B"),
    STD_12_COMMERCE("12_COM"),
    STD_12_SCIENCE_A("12_SCI_A"),
    STD_12_SCIENCE_B("12_SCI_B");

    private final String standard;

    EStandard(String standard) {
        this.standard= standard;
    }

    public String getStandard() {
        return standard;
    }
}
