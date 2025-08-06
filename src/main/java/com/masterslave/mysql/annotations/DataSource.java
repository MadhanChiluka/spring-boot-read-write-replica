package com.masterslave.mysql.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.masterslave.mysql.util.DataSourceType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DataSource {

    DataSourceType value() default DataSourceType.MASTER;
}
