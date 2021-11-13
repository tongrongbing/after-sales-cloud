package com.wk.cloud.core.api;

/**
 * 请求状态码
 * @Auther [li.wang@jsxfedu.com]
 * @Date 2020/1/13 下午12:44
 * @Version 1.0
 * @Copyright (c) 2019 京师讯飞
 */
public enum APIStatus {

    /** 0 Ok. 请求处理成功. */
    OK(0, "OK"),
    /** -1 error. 服务请求失败. */
    ERROR(-1, "服务请求失败"),
    /** 300 Unauthorized Not Activation. 账号未激活. */
    UNAUTHORIZED_300(300, "账号未激活"),
    /** 301 Unauthorized. 未登录或登录状态已失效. */
    UNAUTHORIZED_301(301, "您尚未登录或登录已过期，请重新登录"),
    /** 302 Unauthorized Account Has Been Frozen. 该账号已被冻结. */
    UNAUTHORIZED_302(302, "该账号已被冻结"),
    /** 303 Unauthorized Account does not exist. 账号不存在. */
    UNAUTHORIZED_303(303, "账号不存在"),
    /** 304 Unauthorized The Account Or Password. 账号或密码输入有误,请重新输入. */
    UNAUTHORIZED_304(304, "账号或密码输入有误,请重新输入"),
    /** 305 Unauthorized Continuous Login Failure. 连续登陆错误. */
    UNAUTHORIZED_305(305, "连续登陆错误"),
    /** 306 Unauthorized Token Error. 登录凭证错误 */
    UNAUTHORIZED_306(306, "登录凭证错误"),
    /** 307 Unauthorized Token Expired. 登录凭证过期 */
    UNAUTHORIZED_307(307, "登录凭证过期"),
    UNAUTHORIZED_310(310, "client_id或client_secret错误"),
    /** 330 Incorrect Code. 验证码错误. */
    ERROR_330(330, "验证码错误"),
    /** 331 Code Expired. 验证码已过期. */
    ERROR_331(331, "验证码已过期"),
    ERROR_332(332, "验证码为空"),
    ERROR_333(333, "验证码不存在"),
    ERROR_334(334, "验证码不匹配"),
    /** 400 Bad Request. 请求参数错误. */
    ERROR_400(400, "请求参数错误"),
    /** 400 Bad Request. 请求参数错误. */
    /** 403 Not Found. 禁止访问,没有权限. */
    FORBIDDEN_403(403, "禁止访问,没有权限"),
    /** 406 csrf verify fail csrf 校验失败*/
    ERROR_406(406, "csrf 校验失败"),
    /** 500 Internal Server Error. 服务器内部错误,请求处理失败. */
    ERROR_500(500, "服务器内部错误,请求处理失败"),
    SMS_ERROR_1001(1001, "该手机号短信验证码获取次数超限，请1小时后再试。"),
    SMS_ERROR_1002(1002, "该手机号本日短信验证码获取次数已达到上限。"),
    SMS_ERROR_1003(1003, "该手机号短信验证码太过频繁，请稍后再试。"),
   ;

    private int code;
    private String message;
    APIStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
