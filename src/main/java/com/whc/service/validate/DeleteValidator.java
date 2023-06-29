package com.whc.service.validate;

import com.whc.data.context.DeleteContext;
import com.whc.data.dto.ServiceRequest;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:38
 * @Description:
 */
public interface DeleteValidator {
    DeleteContext buildContext(ServiceRequest request);

    boolean validate(ServiceRequest request);
}
