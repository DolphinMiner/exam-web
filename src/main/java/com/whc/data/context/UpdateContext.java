package com.whc.data.context;

import com.whc.commom.type.UpdateTypeEnum;
import com.whc.data.entity.DemoData;
import lombok.Data;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:40
 * @Description:
 */
@Data
public class UpdateContext {

    private long id;
    private UpdateTypeEnum updateTypeEnum;
    private DemoData data;
}
