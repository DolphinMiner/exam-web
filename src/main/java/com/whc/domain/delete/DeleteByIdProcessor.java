package com.whc.domain.delete;

import com.whc.commom.annotation.DeleteType;
import com.whc.commom.type.DeleteTypeEnum;
import com.whc.data.context.DeleteContext;
import com.whc.data.dto.ServiceResponse;
import com.whc.data.mapper.DataMapper;
import com.whc.domain.DeleteProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 22:13
 * @Description:
 */
@Component
@DeleteType(DeleteTypeEnum.SPECIFIC_ID)
public class DeleteByIdProcessor implements DeleteProcessor {

    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    DataMapper dataMapper;

    public DeleteByIdProcessor(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public ServiceResponse process(DeleteContext context) {
        ServiceResponse serviceResponse = this.buildSuccessfulResponse();

        // process business
        boolean success = dataMapper.deleteById(context.getId());
        // convert response
        if (!success) {
            return this.buildFailedResponse(serviceResponse, "delete failed");
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
