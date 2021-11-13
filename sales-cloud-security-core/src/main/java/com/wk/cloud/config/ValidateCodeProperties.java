package com.wk.cloud.config;

import lombok.Data;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 3:48 下午
 * @description： 验证码配置类
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

}
