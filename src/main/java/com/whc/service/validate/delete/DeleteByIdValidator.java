package com.whc.service.validate.delete;

import com.whc.commom.annotation.DeleteType;
import com.whc.commom.type.DeleteTypeEnum;
import com.whc.data.context.DeleteContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.service.validate.DeleteValidator;
import org.springframework.stereotype.Component;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 22:17
 * @Description:
 */
@Component
@DeleteType(DeleteTypeEnum.SPECIFIC_ID)
public class DeleteByIdValidator implements DeleteValidator {

    @Override
    public DeleteContext buildContext(ServiceRequest request) {
        DeleteContext context = new DeleteContext();
        context.setId(request.getId());
        context.setDeleteTypeEnum(DeleteTypeEnum.SPECIFIC_ID);
        return context;
    }

    @Override
    public boolean validate(ServiceRequest request) {
        if (request.getId() <= 0) {
            return false;
        }
        return true;
    }
}
