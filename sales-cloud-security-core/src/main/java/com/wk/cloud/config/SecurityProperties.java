package com.wk.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 3:50 下午
 * @description： 有关security 配置类
 */
@ConfigurationProperties(prefix = "sales.cloud.security")
public class SecurityProperties {

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
