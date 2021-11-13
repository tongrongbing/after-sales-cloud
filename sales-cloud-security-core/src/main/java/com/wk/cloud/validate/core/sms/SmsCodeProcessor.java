package com.wk.cloud.validate.core.sms;

import com.wk.cloud.validate.core.AbstractValidateCodeProcessor;
import com.wk.cloud.validate.core.ValidateCode;
import com.wk.cloud.validate.core.image.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 9:51 下午
 * @description： 图片验证码处理器
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Resource
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode code) throws Exception {
        String mobile = request.getRequest().getParameter("mobile");
        smsCodeSender.send(mobile,code.getCode());
    }
}
