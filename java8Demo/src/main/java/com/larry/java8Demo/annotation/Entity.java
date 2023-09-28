package com.larry.java8Demo.annotation;

import java.lang.annotation.*;

/**
 * @Target(ElementType.TYPE) - this annotation could only be used in class
 * @Target({ElementType.Type, ElementType.Field}) - could only be used in class and filed
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Entity {
    public String value() default "";
    public boolean isLazyLoad() default true;
}
