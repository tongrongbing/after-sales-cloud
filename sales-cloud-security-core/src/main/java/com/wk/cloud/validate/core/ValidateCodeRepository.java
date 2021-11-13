package com.wk.cloud.validate.core;

import com.wk.cloud.enums.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 2:28 下午
 * @description： 定义一个验证码保存、获取、移除接口服务
 */
public interface ValidateCodeRepository {

    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
