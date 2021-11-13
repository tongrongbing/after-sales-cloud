package com.wk.cloud.validate.core.image;

import com.wk.cloud.enums.ValidateCodeType;
import com.wk.cloud.validate.core.ValidateCode;
import com.wk.cloud.validate.core.ValidateCodeRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 2:32 下午
 * @description： session 存储操作，这是针对pc端，后期可优化为redis 存储操作
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * @author: tongrongbing
     * @description:        将验证码存储到session中
     * @time: 2021/11/10 4:01 下午
     * @param request
     * @param code
     * @param validateCodeType
     * @return void
     */
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(validateCodeType), code);
    }


    /**
     * @author: tongrongbing
     * @description:        构建验证码放入session时的key
     * @time: 2021/11/10 4:03 下午
     * @param validateCodeType
     * @return java.lang.String
     */
    private String getSessionKey(ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

    /**
     * @author: tongrongbing
     * @description:        从session中获取验证码 value
     * @time: 2021/11/10 4:03 下午
     * @param request
     * @param validateCodeType
     * @return com.wk.cloud.validate.core.ValidateCode
     */
    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(validateCodeType));
    }

    /**
     * @author: tongrongbing
     * @description:        移除session中获取验证码
     * @time: 2021/11/10 4:06 下午
     * @param request
     * @param codeType
     * @return void
     */
    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(codeType));
    }
}
