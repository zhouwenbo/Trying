package com.fheebiy.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/12/6.
 *
 * @author bob zhou.
 * Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ActUri {

    String[] path();

    String scheme() default "";

    String host() default "";

}
