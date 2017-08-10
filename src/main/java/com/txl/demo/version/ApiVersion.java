package com.txl.demo.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;
import java.time.Instant;
import java.util.Date;

/**
 * Created by TeaBee on 2017/8/9.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    /**
     * 版本号 暂时用小数表示
     * @return
     */
    double value();

    /**
     * 该版本接口是否在使用中，默认启用
     * @return
     */
    boolean isUsing() default true;

    /**
     * 接口废弃时间，格式为：yyyy-MM-dd HH:mm:ss 到该时间，该版本接口停止使用，默认停止时间2099年
     * @return
     */
    String expireDate() default "2099-1-1 0:0:0";
}