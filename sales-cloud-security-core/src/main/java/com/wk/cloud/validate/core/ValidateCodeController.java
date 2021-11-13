package com.wk.cloud.validate.core;

import com.wk.cloud.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 10:54 上午
 * @description： 验证码请求服务
 */
@RestController
@Slf4j
public class ValidateCodeController {

    @Resource
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * @author: tongrongbing
     * @description:            获取验证码请求
     * @time: 2021/11/10 4:26 下午
     * @param request
     * @param response
     * @param type
     * @return void
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        log.info("【createCode】 type={}",type);
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }


}
