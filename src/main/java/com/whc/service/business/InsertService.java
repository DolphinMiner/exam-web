package com.whc.service.business;

import com.whc.commom.annotation.InsertType;
import com.whc.commom.type.InsertTypeEnum;
import com.whc.data.context.InsertContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.data.dto.ServiceResponse;
import com.whc.domain.InsertProcessor;
import com.whc.service.validate.InsertValidator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:33
 * @Description:
 */
@Service
public class InsertService {

    private static final int SUCCESSFUL = 0;
    private static final int FAILED = 1;

    // validatorImpl
    List<InsertValidator> updateValidatorList;

    // processorImpl
    List<InsertProcessor> processorList;

    public InsertService(List<InsertValidator> updateValidatorList, List<InsertProcessor> processorList) {
        this.updateValidatorList = updateValidatorList;
        this.processorList = processorList;
    }

    public ServiceResponse process(ServiceRequest request) {
        // build fail response
        ServiceResponse serviceResponse = this.buildFailedResponse();

        try {
            // basic validate
            if (request == null) {
                return serviceResponse;
            }

            // specific validate
            boolean validate = false;
            for (InsertValidator validator : updateValidatorList) {
                InsertTypeEnum typeEnum = validator.getClass().getAnnotation(InsertType.class).value();
                if (typeEnum == InsertTypeEnum.DEFAULT) {
                    validate = validator.validate(request);
                }
            }

            // validate failed
            if (!validate) {
                serviceResponse.setMessage("parameter error!");
                return serviceResponse;
            }

            // build Context
            InsertContext context = null;
            for (InsertValidator validator : updateValidatorList) {
                InsertTypeEnum typeEnum = validator.getClass().getAnnotation(InsertType.class).value();
                if (typeEnum == InsertTypeEnum.DEFAULT) {
                    context = validator.buildContext(request);
                }
            }

            // business process
            for (InsertProcessor processor : processorList) {
                InsertTypeEnum typeEnum = processor.getClass().getAnnotation(InsertType.class).value();
                if (typeEnum == InsertTypeEnum.DEFAULT) {
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