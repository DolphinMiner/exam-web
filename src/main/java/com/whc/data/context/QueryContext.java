package com.whc.data.context;

import com.whc.commom.type.QueryTypeEnum;
import lombok.Data;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 16:48
 * @Description:
 */
@Data
public class QueryContext {
    private long id;
    private QueryTypeEnum queryType;
}
