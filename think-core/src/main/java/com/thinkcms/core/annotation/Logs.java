package com.thinkcms.core.annotation;

import com.thinkcms.core.enumerate.LogModule;
import com.thinkcms.core.enumerate.LogOperation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logs {
	String  operation() default "";

	LogModule module() default LogModule.DEFAULT;

	LogOperation operaEnum() default LogOperation.DEFAULT;
}
