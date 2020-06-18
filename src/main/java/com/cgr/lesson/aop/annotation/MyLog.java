package com.cgr.lesson.aop.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: MyLog
 * 自定义日志注解
 * @Author: cgr
 * @UpdateUser: cgr
 * @Version: 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    /**
     * 用户操作哪个模块
     */
    String title() default "";

    /**
     * 记录用户操作的动作
     */
    String action() default "";
}
