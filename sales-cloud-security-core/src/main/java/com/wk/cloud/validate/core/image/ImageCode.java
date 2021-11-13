package com.wk.cloud.validate.core.image;

import com.wk.cloud.validate.core.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/8 10:52 上午
 * @description： 图片验证码
 */
@Data
public class ImageCode extends ValidateCode implements Serializable {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

}
