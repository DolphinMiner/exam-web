package com.whc.commom.type;

public enum UpdateTypeEnum {

    UNKNOWN(999),
    /**
     * 指定id
     */
    SPECIFIC_ID(1),
    /**
     * 指定col_a
     */
    SPECIFIC_COLA(2);


    private final Integer value;

    UpdateTypeEnum(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
