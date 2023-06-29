package com.whc.service.business;

import com.whc.commom.annotation.QueryType;
import com.whc.commom.type.QueryTypeEnum;
import com.whc.data.context.QueryContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.data.dto.ServiceResponse;
import com.whc.domain.QueryProcessor;
import com.whc.service.validate.QueryValidator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 15:25
 * @Description:
 */
@Service
public class QueryService {

    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    // validatorImpl
    List<QueryValidator> queryValidatorList;

    // processorImpl
    List<QueryProcessor> processorList;

    public QueryService(List<QueryValidator> queryValidatorList, List<QueryProcessor> processorList) {
        this.queryValidatorList = queryValidatorList;
        this.processorList = processorList;
    }

    public ServiceResponse process(ServiceRequest request, int queryType) {

        // build fail response
        ServiceResponse serviceResponse = this.buildFailedResponse();

        try {
            // basic validate
            if (request == null) {
                return serviceResponse;
            }

            // specific validate
            boolean validate = false;
            for (QueryValidator queryValidator : queryValidatorList) {
                QueryTypeEnum queryTypeEnum = queryValidator.getClass().getAnnotation(QueryType.class).value();
                if (queryTypeEnum.getValue() == queryType) {
                    validate = queryValidator.validate(request);
                }
            }

            // validate failed
            if (!validate) {
                serviceResponse.setMessage("parameter error!");
                return serviceResponse;
            }

            // build Context
            QueryContext context = null;
            for (QueryValidator queryValidator : queryValidatorList) {
                QueryTypeEnum queryTypeEnum = queryValidator.getClass().getAnnotation(QueryType.class).value();
                if (queryTypeEnum.getValue() == queryType) {
                    context = queryValidator.buildContext(request);
                }
            }

            // business process
            for (QueryProcessor queryProcessor : processorList) {
                QueryTypeEnum queryTypeEnum = queryProcessor.getClass().getAnnotation(QueryType.class).value();
                if (queryTypeEnum.getValue() == queryType) {
                    return queryProcessor.process(context);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            serviceResponse = new ServiceResponse(FAILED, e.getMessage(), null);
        }

        return serviceResponse;
    }


    private ServiceResponse buildSuccessfulResponse() {
        ServiceResponse res = new ServiceResponse();
        res.setCode(SUCCESSFUL);
        res.setMessage("successful");
        return res;
    }

    private ServiceResponse buildFailedResponse() {
        ServiceResponse res = new ServiceResponse();
        res.setCode(FAILED);
        res.setMessage("failed");
        return res;
    }
}
