package com.wk.cloud.validate.core;

import com.wk.cloud.core.api.APIStatus;
import com.wk.cloud.enums.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 9:41 下午
 * @description： 抽象的验证码处理器
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor{

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     * 利用map方式，容器启动时候，会查找ValidateCodeGenerator接口的实现类
     * 也就是说ValidateCodeGenerator 必须有一个或多个实现类
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    /**
     * @author: tongrongbing
     * @description:          创建、保存、发送验证码
     * @time: 2021/11/10 4:11 下午
     * @param request
     * @return void
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * @author: tongrongbing
     * @description:        生成验证码
     * @time: 2021/11/10 4:11 下午
     * @param request
     * @return C
     */
    protected C generate(ServletWebRequest request) {
        //  获取校验码的类型
        String type = getValidateCodeType().toString().toLowerCase();
        // 根据校验码的类型 获取相对应的验证码生成器bean 名称
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        // 根据依赖查找特性，根据bean名称获取验证码生成器
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request); // 调用具体的验证码生成器 生成验证码
    }

    /**
     * @author: tongrongbing
     * @description:            保存验证码
     * @time: 2021/11/10 4:15 下午
     * @param request
     * @param validateCode
     * @return void
     */
    protected void save(ServletWebRequest request, C validateCode) {
        // 配置文件中spring.session.store-type: redis 时候，则保存时候，需要保存到redis中，实体类要序列化；
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType());
    }

    // 提供抽象发送方法，让具体实现的子类 实现 发送验证码
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception ;


    /**
     * @author: tongrongbing
     * @description:         获取校验码的类型
     * @time: 2021/11/10 4:12 下午
     * @return com.wk.cloud.enums.ValidateCodeType
     */
    protected ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * @author: tongrongbing
     * @description:        校验 验证码
     * @time: 2021/11/10 4:17 下午
     * @param request
     * @return void
     */
    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType codeType = getValidateCodeType();
        // 获取session中验证码
        C codeInSession = (C) validateCodeRepository.get(request, codeType);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException(APIStatus.ERROR_330.getMessage());
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(APIStatus.ERROR_332.getMessage());
        }
        if (codeInSession == null) {
            throw new ValidateCodeException(APIStatus.ERROR_333.getMessage());
        }
        if (codeInSession.isExpired()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(APIStatus.ERROR_331.getMessage());
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(APIStatus.ERROR_334.getMessage());
        }

        validateCodeRepository.remove(request, codeType);
    }
}
