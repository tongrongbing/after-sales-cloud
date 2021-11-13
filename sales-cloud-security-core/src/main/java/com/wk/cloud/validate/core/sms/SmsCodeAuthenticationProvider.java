package com.wk.cloud.validate.core.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 10:59 上午
 * @description： 短信验证码认证提供者
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * @author: tongrongbing
     * @description:        进行真正的短信验证
     * @time: 2021/11/10 3:28 下午
     * @param authentication
     * @return org.springframework.security.core.Authentication
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 未认证之前，获取UserDetails用户信息
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(smsCodeAuthenticationToken.getPrincipal().toString());
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        // 认证之后，设置认证信息；setAuthenticated(true)
        SmsCodeAuthenticationToken authenticationTokenRes = new SmsCodeAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationTokenRes.setDetails(userDetails);
        return authenticationTokenRes;
    }

    /**
     * @author: tongrongbing
     * @description:        判断传进来的 authentication是不是SmsCodeAuthenticationToken自定义的子类。
     * @time: 2021/11/10 3:30 下午
     * @param authentication
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
