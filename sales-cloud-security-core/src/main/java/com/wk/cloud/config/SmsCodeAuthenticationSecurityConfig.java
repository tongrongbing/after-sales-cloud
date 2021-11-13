package com.wk.cloud.config;

import com.wk.cloud.validate.core.sms.SmsCodeAuthenticationFilter;
import com.wk.cloud.validate.core.sms.SmsCodeAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 11:10 上午
 * @description： 短信验证码认证配置
 */
@Configuration
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private UserDetailsService userDetailsService;

    /**
     * @author: tongrongbing
     * @description:        将SmsCodeAuthenticationFilter短信验证码过滤器 加入到security过滤器中
     *                      且是 放到UsernamePasswordAuthenticationFilter过滤器之前。
     * @time: 2021/11/10 3:24 下午
     * @param http
     * @return void
     */
    @Override
    public void configure(HttpSecurity http) {
        // 定义短信验证码过滤器
        SmsCodeAuthenticationFilter authenticationFilter = new SmsCodeAuthenticationFilter();
        // 设置AuthenticationManager，需要它进行authenticate();
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        // 设置成功或失败处理器
        authenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        // 设置自定义的认证提供者
        SmsCodeAuthenticationProvider authenticationProvider = new SmsCodeAuthenticationProvider();
        // 认证提供者中需要的userDetailsService
        authenticationProvider.setUserDetailsService(userDetailsService);
        // 将认证提供者和验证码过滤器加入到security过滤器中。
        http.authenticationProvider(authenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
