package com.whc.service.business;

import com.whc.commom.annotation.UpdateType;
import com.whc.commom.type.UpdateTypeEnum;
import com.whc.data.context.UpdateContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.data.dto.ServiceResponse;
import com.whc.domain.UpdateProcessor;
import com.whc.service.validate.UpdateValidator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:34
 * @Description:
 */
@Service
public class UpdateService {
    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    // validatorImpl
    List<UpdateValidator> updateValidatorList;

    // processorImpl
    List<UpdateProcessor> processorList;

    public UpdateService(List<UpdateValidator> updateValidatorList, List<UpdateProcessor> processorList) {
        this.updateValidatorList = updateValidatorList;
        this.processorList = processorList;
    }

    public ServiceResponse process(ServiceRequest request, int type) {
        // build fail response
        ServiceResponse serviceResponse = this.buildFailedResponse();

        try {
            // basic validate
            if (request == null) {
                return serviceResponse;
            }

            // specific validate
            boolean validate = false;
            for (UpdateValidator validator : updateValidatorList) {
                UpdateTypeEnum typeEnum = validator.getClass().getAnnotation(UpdateType.class).value();
                if (typeEnum.getValue() == type) {
                    validate = validator.validate(request);
                }
            }

            // validate failed
            if (!validate) {
                serviceResponse.setMessage("parameter error!");
                return serviceResponse;
            }

            // build Context
            UpdateContext context = null;
            for (UpdateValidator validator : updateValidatorList) {
                UpdateTypeEnum typeEnum = validator.getClass().getAnnotation(UpdateType.class).value();
                if (typeEnum.getValue() == type) {
                    context = validator.buildContext(request);
                }
            }

            // business process
            for (UpdateProcessor processor : processorList) {
                UpdateTypeEnum typeEnum = processor.getClass().getAnnotation(UpdateType.class).value();
                if (typeEnum.getValue() == type) {
                    return processor.process(context);
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

