package com.wk.cloud.config;
import lombok.Data;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 3:46 下午
 * @description： 短信验证码配置类
 */
@Data
public class SmsCodeProperties {
    /**
     *  长度
     */
    private int length = 4;

    /**
     *  过期时间
     */
    private int expireIn = 60;

    /**
     *  需要进行短信验证码验证的url请求
     */
    private String url;

}
