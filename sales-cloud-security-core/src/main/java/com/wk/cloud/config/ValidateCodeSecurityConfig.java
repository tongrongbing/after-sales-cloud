package com.wk.cloud.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 2:47 下午
 * @description： 校验码相关安全配置，目的是将自定义的ValidateCodeFilter加入到security过滤器链中
 */
@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Resource
    private Filter validateCodeFilter;

    /**
     * @author: tongrongbing
     * @description:            将validateCodeFilter加在AbstractPreAuthenticatedProcessingFilter过滤器之前
     * @time: 2021/11/10 3:21 下午
     * @param http
     * @return void
     */
    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
