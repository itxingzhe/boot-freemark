package cn.wyb.personal.annotation;

import java.lang.annotation.*;

/**
 * MethodLog: 日志切面注解.
 *
 * @author wangyibin
 * @date 2018/8/21 14:28
 * @see
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {

	String remark() default "";

	String operationType() default "0";

	String desc() default "";
}
