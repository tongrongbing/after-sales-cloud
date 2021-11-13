package com.wk.cloud.validate.core;

import org.springframework.security.core.AuthenticationException;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 11:43 上午
 * @description： 自定义验证码异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
