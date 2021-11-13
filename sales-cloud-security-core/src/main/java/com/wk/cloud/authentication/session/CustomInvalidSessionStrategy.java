package com.wk.cloud.authentication.session;

import com.alibaba.fastjson.JSON;
import com.wk.cloud.core.api.APIStatus;
import com.wk.cloud.core.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 5:24 下午
 * @description： 自定义session 失效管理策略
 */
@Slf4j
@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy{

    /**
     * @author: tongrongbing
     * @description:        将浏览器中的cookie JSESSIONID清除掉
     * @time: 2021/11/10 3:45 下午
     * @param request
     * @param response
     * @return void
     */
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("session invalid process...");
        // 1、将浏览器中的cookie JSESSIONID清除掉
        cancelCookie(request,response);
        response.setContentType("application/json;charset=UTF-8");
        R ok = R.error(APIStatus.UNAUTHORIZED_301.getCode(),APIStatus.UNAUTHORIZED_301.getMessage());
        response.getWriter().write(JSON.toJSONString(ok));
    }

    private void cancelCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath(getCookiePath(request));
        response.addCookie(cookie);
    }

    private String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
}
