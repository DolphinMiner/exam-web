package com.whc.domain.update;

import com.whc.commom.annotation.UpdateType;
import com.whc.commom.type.UpdateTypeEnum;
import com.whc.data.context.UpdateContext;
import com.whc.data.dto.ServiceResponse;
import com.whc.data.mapper.DataMapper;
import com.whc.domain.UpdateProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:40
 * @Description:
 */
@Component
@UpdateType(UpdateTypeEnum.SPECIFIC_ID)
public class UpdateByIdProcessor implements UpdateProcessor {
    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    DataMapper dataMapper;

    public UpdateByIdProcessor(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public ServiceResponse process(UpdateContext context) {
        ServiceResponse serviceResponse = this.buildSuccessfulResponse();

        // process business
        boolean success = dataMapper.updateById(context.getId(),
                context.getData().getColA(),
                context.getData().isColB(),
                context.getData().getColC());

        // convert response
        if (!success) {
            this.buildFailedResponse(serviceResponse, "query no result");
        }

        serviceResponse.getData().add(context.getData());

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
