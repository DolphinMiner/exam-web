package com.whc.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Result;

import java.sql.Timestamp;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 13:26
 * @Description:
 */
@Data
public class DemoData {
    /**
     * primary key
     */
    private int id;
    /**
     *  col_A
     */
    private String colA;
    /**
     *  col_B
     */
    private boolean colB;
    /**
     *  col_C
     */
    private Timestamp colC;

}
