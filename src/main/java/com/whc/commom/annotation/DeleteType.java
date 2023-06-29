package com.whc.commom.annotation;

import com.whc.commom.type.DeleteTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeleteType {
    DeleteTypeEnum value();
}
