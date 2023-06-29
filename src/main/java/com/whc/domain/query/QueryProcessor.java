package com.whc.domain.query;

import com.whc.data.context.QueryContext;
import com.whc.data.dto.ServiceResponse;


public interface QueryProcessor {
    ServiceResponse process(QueryContext context);
}