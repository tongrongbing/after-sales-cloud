package com.wk.cloud.validate.core.image;

import com.wk.cloud.validate.core.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 9:51 下午
 * @description： 图片验证码处理器
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * @author: tongrongbing
     * @description:        发送验证码，这里是写io流到浏览器
     * @time: 2021/11/10 3:59 下午
     * @param request
     * @param imageCode
     * @return void
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
