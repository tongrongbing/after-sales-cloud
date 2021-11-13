package com.wk.cloud.validate.core;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 9:43 下午
 * @description： 验证码
 */
public class ValidateCode implements Serializable {
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(){}
    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
