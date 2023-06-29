package com.whc.domain;

import com.whc.data.context.DeleteContext;
import com.whc.data.dto.ServiceResponse;


public interface DeleteProcessor {
    ServiceResponse process(DeleteContext context);
}
