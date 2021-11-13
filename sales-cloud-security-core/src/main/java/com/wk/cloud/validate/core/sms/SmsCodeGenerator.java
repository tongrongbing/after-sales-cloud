package com.wk.cloud.validate.core.sms;

import com.wk.cloud.config.SecurityProperties;
import com.wk.cloud.validate.core.ValidateCode;
import com.wk.cloud.validate.core.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 4:30 下午
 * @description：短信验证码生成器
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(5);
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }


}
