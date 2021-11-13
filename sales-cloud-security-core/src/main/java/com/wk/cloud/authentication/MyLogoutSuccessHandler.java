package com.wk.cloud.authentication;

import com.alibaba.fastjson.JSON;
import com.wk.cloud.core.api.APIStatus;
import com.wk.cloud.core.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 4:05 下午
 * @description： 自定义成功登出处理器
 */
@Component("logoutSuccessHandler")
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("logout success...");
        response.setContentType("application/json;charset=UTF-8");
        R ok = R.ok(APIStatus.OK);
        response.getWriter().write(JSON.toJSONString(ok));
    }
}
