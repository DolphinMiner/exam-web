package com.whc.data.context;

import com.whc.commom.type.InsertTypeEnum;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:40
 * @Description:
 */
@Data
public class InsertContext {

    private String colA;

    private boolean colB;

    private Timestamp colC;

    private InsertTypeEnum insertTypeEnum;
}
