package com.omarahmed42.newecomservlets.enums;

public enum UserType {
    STUDENT("STUDENT", 1), ADMINISTRATOR("ADMINISTRATOR", 2), STAFF("STAFF", 3);

    private final String text;
    private final int value;

    UserType(final String text, final int value){
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
