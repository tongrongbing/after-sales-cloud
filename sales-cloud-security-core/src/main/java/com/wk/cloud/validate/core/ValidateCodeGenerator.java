package com.wk.cloud.validate.core;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 4:28 下午
 * @description： 验证码生成器
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
