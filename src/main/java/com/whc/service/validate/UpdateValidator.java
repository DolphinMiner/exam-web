package com.whc.service.validate;

import com.whc.data.context.UpdateContext;
import com.whc.data.dto.ServiceRequest;

public interface UpdateValidator {

    UpdateContext buildContext(ServiceRequest request);

    boolean validate(ServiceRequest request);
}
