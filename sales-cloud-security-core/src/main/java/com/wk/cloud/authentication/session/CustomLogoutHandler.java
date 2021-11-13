package com.wk.cloud.authentication.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/10 1:56 下午
 * @description： 自定义登出处理器
 */
@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 清除session信息，原因是并没有从 SessionRegistryImpl.principals 移除用户信息
        String sessionId = request.getRequestedSessionId();
        sessionRegistry.removeSessionInformation(sessionId);
    }
}
