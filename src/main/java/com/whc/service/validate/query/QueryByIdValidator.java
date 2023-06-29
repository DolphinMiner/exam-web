package com.whc.service.validate.query;

import com.whc.commom.annotation.QueryType;
import com.whc.commom.type.QueryTypeEnum;
import com.whc.data.context.QueryContext;
import com.whc.service.validate.QueryValidator;
import com.whc.data.dto.ServiceRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 16:07
 * @Description:
 */
@Component
@QueryType(QueryTypeEnum.SPECIFIC_ID)
public class QueryByIdValidator implements QueryValidator {

    @Override
    public QueryContext buildContext(ServiceRequest request) {
        QueryContext context = new QueryContext();
        context.setId(request.getId());
        context.setQueryType(QueryTypeEnum.SPECIFIC_ID);
        return context;
    }

    @Override
    public boolean validate(ServiceRequest request) {
        if(request.getId() < 0){
            return false;
        }
        return true;
    }
}
