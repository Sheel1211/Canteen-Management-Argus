package com.argus.cms.batchManagement;

public enum TimeSlot {
    BATCH_1("13:00 - 13:15"),
    BATCH_2("13:15 - 13:30"),
    BATCH_3("13:30 - 13:45"),
    BATCH_4("13:45 - 14:00");
    private final String slot;

    TimeSlot(String slot) {
        this.slot = slot;
    }
}