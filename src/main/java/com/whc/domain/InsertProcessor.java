package com.whc.domain;

import com.whc.data.context.InsertContext;
import com.whc.data.dto.ServiceResponse;

public interface InsertProcessor {

    ServiceResponse process(InsertContext context);
}
