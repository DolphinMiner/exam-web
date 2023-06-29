package com.whc.service.validate;

import com.whc.data.context.QueryContext;
import com.whc.data.dto.ServiceRequest;

public interface QueryValidator {

    QueryContext buildContext(ServiceRequest request);

    boolean validate(ServiceRequest request);

}
