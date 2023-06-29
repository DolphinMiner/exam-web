package com.whc.commom.annotation;

import com.whc.commom.type.InsertTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InsertType {
    InsertTypeEnum value();
}
