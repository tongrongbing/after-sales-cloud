package com.wk.cloud.validate.core;

import com.wk.cloud.enums.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/9 2:22 下午
 * @description： 验证码处理器助手，通过holder来获取验证码处理器
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;


    /**
     * @author: tongrongbing
     * @description:          根据验证码类型获取 验证码处理器
     * @time: 2021/11/10 4:25 下午
     * @param type
     * @return com.wk.cloud.validate.core.ValidateCodeProcessor
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     * @author: tongrongbing
     * @description:        根据type验证码处理器
     * @time: 2021/11/10 4:26 下午
     * @param type
     * @return com.wk.cloud.validate.core.ValidateCodeProcessor
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }


}
