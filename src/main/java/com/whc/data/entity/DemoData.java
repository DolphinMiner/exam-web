package com.whc.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 13:26
 * @Description:
 */
@Data
@AllArgsConstructor
public class DemoData {
    /**
     * primary key
     */
    private long id;
    /**
     * col_A
     */
    private String colA;
    /**
     * col_B
     */
    private boolean colB;
    /**
     * col_C
     */
    private Timestamp colC;

}
