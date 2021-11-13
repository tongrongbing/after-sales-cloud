package com.wk.cloud.authentication;

import com.alibaba.fastjson.JSON;
import com.wk.cloud.core.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/7 9:44 下午
 * @description： 登陆成功处理器
 */
@Component("myAuthenticationSuccessHandler")
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("login success...");
        response.setContentType("application/json;charset=UTF-8");
        R ok = R.ok(authentication);
        response.getWriter().write(JSON.toJSONString(ok));
    }


}
