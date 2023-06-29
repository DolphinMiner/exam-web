package com.whc.service.validate.query;

import com.whc.commom.annotation.QueryType;
import com.whc.commom.type.QueryTypeEnum;
import com.whc.data.context.QueryContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.service.validate.QueryValidator;
import org.springframework.stereotype.Component;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 19:24
 * @Description:
 */
@Component
@QueryType(QueryTypeEnum.ALL)
public class QueryAllValidator implements QueryValidator {

    @Override
    public QueryContext buildContext(ServiceRequest request) {
        QueryContext context = new QueryContext();
        context.setId(request.getId());
        context.setQueryType(QueryTypeEnum.ALL);
        return context;
    }

    @Override
    public boolean validate(ServiceRequest request) {
        return true;
    }
}
