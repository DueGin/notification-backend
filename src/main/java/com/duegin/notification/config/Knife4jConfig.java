package com.duegin.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author DueGin
 */
@Configuration
@EnableOpenApi
public class Knife4jConfig {

    @Value("${swagger.enable:false}")
    private Boolean isEnableSwagger;

    @Bean
    public Docket createRestApi() {
        // 文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(isEnableSwagger)
                .apiInfo(apiInfo())
                .select()
                // 填全类包名
                .apis(RequestHandlerSelectors.basePackage("com.duegin.notification.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Channel")
                .version("1.0")
                .description("Channel Document")
                .build();
    }
}