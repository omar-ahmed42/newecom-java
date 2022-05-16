package com.omarahmed42.newecomservlets.enums;

public enum AcademicYear {
    FIRST("FIRST", 1), SECOND("SECOND", 2), THIRD("THIRD", 3), FOURTH("FOURTH", 4);

    private final String text;
    private final int value;

    AcademicYear(final String text, final int value){
        this.text = text;
        this.value = value;
    }

    @Override
    public String toString(){
        return text;
    }

    public int getValue() {
        return value;
    }
}
