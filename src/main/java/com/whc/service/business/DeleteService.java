package com.whc.service.business;

import com.whc.commom.annotation.DeleteType;
import com.whc.commom.type.DeleteTypeEnum;
import com.whc.data.context.DeleteContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.data.dto.ServiceResponse;
import com.whc.domain.DeleteProcessor;
import com.whc.service.validate.DeleteValidator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:34
 * @Description:
 */
@Service
public class DeleteService {

    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    // validatorImpl
    List<DeleteValidator> updateValidatorList;

    // processorImpl
    List<DeleteProcessor> processorList;

    public DeleteService(List<DeleteValidator> updateValidatorList, List<DeleteProcessor> processorList) {
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
            for (DeleteValidator validator : updateValidatorList) {
                DeleteTypeEnum typeEnum = validator.getClass().getAnnotation(DeleteType.class).value();
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
            DeleteContext context = null;
            for (DeleteValidator validator : updateValidatorList) {
                DeleteTypeEnum typeEnum = validator.getClass().getAnnotation(DeleteType.class).value();
                if (typeEnum.getValue() == type) {
                    context = validator.buildContext(request);
                }
            }

            // business process
            for (DeleteProcessor processor : processorList) {
                DeleteTypeEnum typeEnum = processor.getClass().getAnnotation(DeleteType.class).value();
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
