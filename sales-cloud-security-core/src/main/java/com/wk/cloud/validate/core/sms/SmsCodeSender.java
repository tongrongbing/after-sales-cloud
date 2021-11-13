package com.wk.cloud.validate.core.sms;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 9:44 上午
 * @description：
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}
