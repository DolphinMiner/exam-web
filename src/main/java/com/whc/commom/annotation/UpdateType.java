package com.whc.commom.annotation;

import com.whc.commom.type.UpdateTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateType {
    UpdateTypeEnum value();
}
