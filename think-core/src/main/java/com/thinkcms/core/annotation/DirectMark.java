package com.thinkcms.core.annotation;

import com.thinkcms.core.constants.DirectiveNameEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DirectMark {
	String  name() default "";

	String  desc() default "";

	DirectiveNameEnum[] groups() default {};
}
