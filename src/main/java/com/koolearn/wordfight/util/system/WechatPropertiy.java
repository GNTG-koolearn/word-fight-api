package com.koolearn.wordfight.util.system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/3
 * Time: 16:29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatPropertiy {

    private String appid;

    private String appSecret;

    private String loginUrl;
}
