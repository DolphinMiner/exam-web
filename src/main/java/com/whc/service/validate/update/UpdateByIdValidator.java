package com.whc.service.validate.update;

import com.mysql.cj.util.StringUtils;
import com.whc.commom.annotation.UpdateType;
import com.whc.commom.type.UpdateTypeEnum;
import com.whc.data.context.UpdateContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.data.entity.DemoData;
import com.whc.service.validate.UpdateValidator;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 20:52
 * @Description:
 */
@Component
@UpdateType(UpdateTypeEnum.SPECIFIC_ID)
public class UpdateByIdValidator implements UpdateValidator {

    @Override
    public UpdateContext buildContext(ServiceRequest request) {
        UpdateContext context = new UpdateContext();
        context.setId(request.getId());
        Timestamp timestamp = Timestamp.valueOf(request.getColC());
        context.setData(new DemoData(request.getId(), request.getColA(), request.isColB(), timestamp));
        return context;
    }

    @Override
    public boolean validate(ServiceRequest request) {
        if (request.getId() <= 0) {
            return false;
        }
        if (StringUtils.isNullOrEmpty(request.getColC())) {
            return false;
        }
        return true;
    }

}
