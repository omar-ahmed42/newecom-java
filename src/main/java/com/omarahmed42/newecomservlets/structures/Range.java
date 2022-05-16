package com.omarahmed42.newecomservlets.structures;

public class Range {
    public Long min;
    public Long max;
    public boolean isMinNextID;


    public Range(Long min, Long max) {
        this.min = min;
        this.max = max;
        this.isMinNextID = false;
    }

    public Range(Long min, Long max, boolean isMinNextID) {
        this.min = min;
        this.max = max;
        this.isMinNextID = isMinNextID;
    }

    public Range() {
        isMinNextID = false;
    }
}
