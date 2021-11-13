package com.wk.cloud.authentication;

import com.alibaba.fastjson.JSON;
import com.wk.cloud.core.api.APIStatus;
import com.wk.cloud.core.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/7 10:05 下午
 * @description： 登陆失败处理器
 */
@Component("/myAuthenticationFailureHandler")
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.info("login fail...");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(R.error(APIStatus.UNAUTHORIZED_304.getCode(),exception.getMessage())));
    }
}
