package com.wk.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/10 2:51 下午
 * @description：  security 涉及到的bean配置
 */
@Component
public class SecurityBeanConfig {

    @Resource
    private DataSource dataSource;

    /**
     * @author: tongrongbing
     * @description:        spring security 密码加密配置
     * @time: 2021/11/10 2:52 下午
     * @param
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 设置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    /**
     * @author: tongrongbing
     * @description:        spring security 记住我功能 bean配置，持久化到数据库中（persistent_logins）
     * @time: 2021/11/10 2:52 下午
     * @param
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 设置为true，表示系统初始化会在数据库中创建表，所以第二次启动项目时候，需要将其注释掉
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    /**
     * @author: tongrongbing
     * @description:        配置security session 管理策略，主要是用于，当登出时候，removeSessionInformation移除掉用户session信息
     * @time: 2021/11/10 2:56 下午
     * @param
     * @return org.springframework.security.core.session.SessionRegistry
     */
    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
}
