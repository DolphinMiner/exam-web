package com.whc.commom.type;

public enum QueryTypeEnum {
    UNKNOWN(999),
    /**
     * 指定id
     */
    SPECIFIC_ID(1),
    /**
     * 指定col_a
     */
    SPECIFIC_COLA(2),
    /**
     * 查询所有
     */
    ALL(0);

    private final Integer value;

    QueryTypeEnum(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
