package com.wk.cloud.config;

import com.wk.cloud.constants.SecurityConstants;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 2:43 下午
 * @description： 表单登陆验证
 */
@Component
public class FormAuthenticationConfig {

    @Resource
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * @author: tongrongbing
     * @description:
     * @time: 2021/11/10 3:01 下午
     * @param http
     * @return void
     */
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()   // 表示表单登陆
                // 当请求需要身份认证时，默认跳转的url，对应SecurityController中处理。没有认证，响应json，告诉前端，跳转到登陆页面
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM) // 执行登陆时，用户名密码登录请求处理url
                .successHandler(myAuthenticationSuccessHandler)  // 登陆成功处理器
                .failureHandler(myAuthenticationFailureHandler);  //  登陆失败处理器
    }

}
