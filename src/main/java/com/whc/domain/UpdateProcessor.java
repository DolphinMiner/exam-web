package com.whc.domain;

import com.whc.data.context.UpdateContext;
import com.whc.data.dto.ServiceResponse;

public interface UpdateProcessor {

    ServiceResponse process(UpdateContext context);
}
