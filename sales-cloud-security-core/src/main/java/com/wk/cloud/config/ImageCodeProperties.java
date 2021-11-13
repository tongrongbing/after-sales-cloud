package com.wk.cloud.config;
import lombok.Data;
/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 3:46 下午
 * @description：  图片验证码配置类
 */
@Data
public class ImageCodeProperties {
    /**
     *  宽度
     */
    private int width = 67;

    /**
     *  高度
     */
    private int height = 23;

    /**
     *  长度
     */
    private int length = 4;

    /**
     *  过期时间
     */
    private int expireIn = 60;

    /**
     *  需要进行图片验证码验证的url请求
     */
    private String url;

}
