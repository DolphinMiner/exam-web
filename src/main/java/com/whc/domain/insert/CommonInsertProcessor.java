package com.whc.domain.insert;

import com.whc.commom.annotation.InsertType;
import com.whc.commom.type.InsertTypeEnum;
import com.whc.data.context.InsertContext;
import com.whc.data.dto.ServiceResponse;
import com.whc.data.mapper.DataMapper;
import com.whc.domain.InsertProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 22:32
 * @Description:
 */
@Component
@InsertType(InsertTypeEnum.DEFAULT)
public class CommonInsertProcessor implements InsertProcessor {

    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    DataMapper dataMapper;

    public CommonInsertProcessor(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public ServiceResponse process(InsertContext context) {

        ServiceResponse serviceResponse = this.buildSuccessfulResponse();

        // process business
        boolean success = dataMapper.insert(
                context.getColA(),
                context.isColB(),
                context.getColC());

        // convert response
        if (!success) {
            this.buildFailedResponse(serviceResponse, "insert failed");
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
