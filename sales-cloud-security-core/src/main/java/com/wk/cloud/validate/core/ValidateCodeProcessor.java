package com.wk.cloud.validate.core;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 9:37 下午
 * @description： 校验码处理器，封装不同校验码的处理逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * @author: tongrongbing
     * @description:        创建校验码
     * @time: 2021/11/10 4:10 下午
     * @param request
     * @return void
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * @author: tongrongbing
     * @description:        校验验证码
     * @time: 2021/11/10 4:10 下午
     * @param servletWebRequest
     * @return void
     */

    void validate(ServletWebRequest servletWebRequest);
}
