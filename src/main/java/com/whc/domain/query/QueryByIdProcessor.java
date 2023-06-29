package com.whc.domain.query;

import com.whc.commom.annotation.QueryType;
import com.whc.commom.type.QueryTypeEnum;
import com.whc.data.context.QueryContext;
import com.whc.data.dto.ServiceResponse;
import com.whc.data.entity.DemoData;
import com.whc.data.mapper.DataMapper;
import com.whc.domain.QueryProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 15:31
 * @Description:
 */
@Component
@QueryType(QueryTypeEnum.SPECIFIC_ID)
public class QueryByIdProcessor implements QueryProcessor {

    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    DataMapper dataMapper;

    public QueryByIdProcessor(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public ServiceResponse process(QueryContext context) {

        ServiceResponse serviceResponse = this.buildSuccessfulResponse();

        // process business
        DemoData demoData = dataMapper.queryById(context.getId());
        // convert response
        if (demoData == null) {
            this.buildFailedResponse(serviceResponse, "query no result");
        } else {
            serviceResponse.getData().add(demoData);
        }
        return serviceResponse;
    }


    private ServiceResponse buildSuccessfulResponse() {
        ServiceResponse res = new ServiceResponse();
        res.setCode(SUCCESSFUL);
        res.setMessage("process successful");
        res.setData(new ArrayList<>());
        return res;
    }

    private ServiceResponse buildFailedResponse(ServiceResponse res, String message) {
        res.setCode(FAILED);
        res.setMessage(message);
        return res;
    }
}
