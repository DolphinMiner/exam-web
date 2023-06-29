package com.whc.service.validate.insert;

import com.mysql.cj.util.StringUtils;
import com.whc.commom.annotation.InsertType;
import com.whc.commom.type.InsertTypeEnum;
import com.whc.data.context.InsertContext;
import com.whc.data.dto.ServiceRequest;
import com.whc.service.validate.InsertValidator;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 22:38
 * @Description:
 */
@Component
@InsertType(InsertTypeEnum.DEFAULT)
public class CommonInsertValidator implements InsertValidator {
    @Override
    public InsertContext buildContext(ServiceRequest request) {
        InsertContext insertContext = new InsertContext();
        Timestamp timestamp = Timestamp.valueOf(request.getColC());
        insertContext.setInsertTypeEnum(InsertTypeEnum.DEFAULT);
        insertContext.setColA(request.getColA());
        insertContext.setColB(request.isColB());
        insertContext.setColC(timestamp);
        return insertContext;
    }

    @Override
    public boolean validate(ServiceRequest request) {
        if (request.getColA() == null || StringUtils.isNullOrEmpty(request.getColC())) {
            return false;
        }
        return true;
    }
}
