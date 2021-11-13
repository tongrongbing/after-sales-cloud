package com.wk.cloud.validate.core.sms;

import org.springframework.stereotype.Component;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 9:45 上午
 * @description： 默认的短信验证码发送器
 */
@Component
public class DefaultSmsCodeSender implements SmsCodeSender{

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送验证码："+code);
    }
}
