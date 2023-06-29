package com.whc.service.validate;

import com.whc.data.context.InsertContext;
import com.whc.data.dto.ServiceRequest;

public interface InsertValidator {
    InsertContext buildContext(ServiceRequest request);

    boolean validate(ServiceRequest request);
}
