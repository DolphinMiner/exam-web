package com.whc.data.context;

import com.whc.commom.type.DeleteTypeEnum;
import lombok.Data;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:41
 * @Description:
 */
@Data
public class DeleteContext {
    private long id;
    private DeleteTypeEnum deleteTypeEnum;
}
