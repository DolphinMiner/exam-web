package com.whc.commom.annotation;

import com.whc.commom.type.QueryTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryType {

    QueryTypeEnum value();
}
