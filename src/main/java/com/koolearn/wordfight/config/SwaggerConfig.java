package com.koolearn.wordfight.config;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
@Slf4j
public class SwaggerConfig {

    public static final String PROD = "prod";

    @Autowired
    private Environment env;

    @Bean
    public Docket api() {
        String[] profile = env.getActiveProfiles();
        log.info("profile:{}", Arrays.toString(profile));
        String host = "";
        if (profile!=null&&profile.length>0&&PROD.equals(profile[0])) {
            host = "www.chenjinxinlove.com/word-fight";
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .protocols(Sets.newHashSet("http","https"))
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.koolearn.wordfight.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")
                .description("restful 风格接口")
                //服务条款网址
                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                //.contact(new Contact("帅呆了", "url", "email"))
                .build();
    }
}