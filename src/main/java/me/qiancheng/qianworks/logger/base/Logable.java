package me.qiancheng.qianworks.logger.base;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author <a href="i@qiancheng.me">千橙</a>
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface Logable {
}
