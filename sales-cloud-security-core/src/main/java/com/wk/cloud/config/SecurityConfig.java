package com.wk.cloud.config;

import com.wk.cloud.authentication.session.CustomLogoutHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.annotation.Resource;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/7 6:25 下午
 * @description： 有关security 配置
 */
@Configuration
@EnableWebSecurity // 启动springSecurity过滤器链功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Resource
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Resource
    private FormAuthenticationConfig formAuthenticationConfig;

    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;

    @Resource
    private InvalidSessionStrategy invalidSessionStrategy;

    @Resource
    private CustomLogoutHandler customLogoutHandler;

    @Resource
    private PersistentTokenRepository persistentTokenRepository;

    @Resource
    private SessionRegistry sessionRegistry;

    /**
     * 资源权限配置(过滤器链):
     * 1、被拦截的资源
     * 2、资源所对应的角色权限
     * 3、定义认证方式:httpBasic 、httpForm
     * 4、定制登录页面、登录请求地址、错误处理方式
     * 5、自定义 spring security 过滤器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfig.configure(http); // 配置表单登陆验证
        http.apply(validateCodeSecurityConfig) // 将自定义的图片验证码过滤器加入到security过滤器中
                .and()
                .apply(smsCodeAuthenticationSecurityConfig) //将自定义的短信验证码过滤器加入到security过滤器中
                    .and()
              .rememberMe()  // 记住我功能，前端页面标签必须 remember-me，security中固定的
                    .tokenRepository(persistentTokenRepository)  // 设置记住我功能数据库存储
                    .userDetailsService(userDetailsService)     //  设置获取用户信息service
                    .tokenValiditySeconds(3600)                 // 记住我功能失效时间：3600s
                    .and()
              .sessionManagement()                              // session管理
                    .invalidSessionStrategy(invalidSessionStrategy)  //session 失效管理策略
                    //.maximumSessions(1)  系统中最大允许用户session个数：1个
                    //.sessionRegistry(sessionRegistry)  // session策略
                    //.and()
                    .and()
                .logout()     // 登出
                    .logoutSuccessHandler(logoutSuccessHandler) // 自定义成功登出处理器
                    .addLogoutHandler(customLogoutHandler) // 自定义登出处理器
                    .deleteCookies("JSESSIONID")           // 删除cookie中的JSESSIONID信息
                    .and()
               // .antMatchers("/main/hello").ha
                .authorizeRequests()
                .antMatchers("/authentication/require","/login.html","/code/*").permitAll() // 哪些url不要认证
                .antMatchers("/main/hello").hasAnyRole("ADMIN")
                .anyRequest().authenticated() // 其他所有的请求需要身份认证放在最后面
                .and()
                .csrf().disable()
                ;

    }


}
