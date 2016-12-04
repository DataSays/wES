package org.datasays.db;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface WTable {

	/**
	 * Table or view name.
	 */
	String value() default "";

	/**
	 * Schema name.
	 */
	String schema() default "";
}
