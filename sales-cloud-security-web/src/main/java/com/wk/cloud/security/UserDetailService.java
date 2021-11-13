package com.wk.cloud.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @author：tongrongbing
 * @date：created in 2021/11/7 7:11 下午
 * @description：
 */
@Component("userDetailsService")
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 根据请求用户名向数据库中查询用户信息 if(!"meng".equalsIgnoreCase(username)) {
        // 如果有此用户信息, 假设数据库查询到的用户密码是 1234 String password = passwordEncoder.encode("1234");
        // 2.查询用户拥有权限
        // 3.封装用户信息: username用户名,password数据库中的密码,authorities资源权限标识符 // SpringSecurity 底层会校验是否身份合法。

        return new User(username,passwordEncoder.encode("1234"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
