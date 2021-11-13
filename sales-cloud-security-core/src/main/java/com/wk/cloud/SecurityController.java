package com.wk.cloud;

import com.wk.cloud.core.api.APIStatus;
import com.wk.cloud.core.api.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/7 9:15 下午
 * @description： 未认证的请求controller
 */
@RestController
@Slf4j
public class SecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * @author: tongrongbing
     * @description:        没有经过认证的请求，security会跳转到这个方法，告诉前端跳转到登陆页面
     * @time: 2021/11/7 9:28 下午
     * @param request
     * @param response
     * @return com.wk.cloud.core.api.R
     */
    @RequestMapping("/authentication/require")
    public R requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception{
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) { // 模拟登陆
            log.info("request url={}",savedRequest.getRedirectUrl());
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/login.html");
            }
        }
        return new R(APIStatus.UNAUTHORIZED_301);
    }

}
