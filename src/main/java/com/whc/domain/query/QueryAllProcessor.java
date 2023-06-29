package com.whc.domain.query;

import com.whc.commom.annotation.QueryType;
import com.whc.commom.type.QueryTypeEnum;
import com.whc.data.context.QueryContext;
import com.whc.data.dto.ServiceResponse;
import com.whc.data.entity.DemoData;
import com.whc.data.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 15:32
 * @Description:
 */
@Component
@QueryType(QueryTypeEnum.ALL)
public class QueryAllProcessor implements QueryProcessor {

    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;
    // Dao
    @Autowired
    DataMapper dataMapper;


    @Override
    public ServiceResponse process(QueryContext context) {
        ServiceResponse serviceResponse = this.buildSuccessfulResponse();

        // process business
        ArrayList<DemoData> demoData = dataMapper.queryAll();

        // convert response
        serviceResponse.setData(demoData);

        if(CollectionUtils.isEmpty(serviceResponse.getData())){
            this.buildFailedResponse(serviceResponse, "query no result");
        }
        return serviceResponse;
    }


    private ServiceResponse buildSuccessfulResponse() {
        ServiceResponse res = new ServiceResponse();
        res.setCode(SUCCESSFUL);
        res.setMessage("successful");
        return res;
    }

    private ServiceResponse buildFailedResponse(ServiceResponse res, String message) {
        res.setCode(FAILED);
        res.setMessage(message);
        return res;
    }
}
