package com.whc.commom.type;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:42
 * @Description:
 */
public enum InsertTypeEnum {
    UNKNOWN(999),

    /**
     * 默认
     */
    DEFAULT(0);

    private final Integer value;

    InsertTypeEnum(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
