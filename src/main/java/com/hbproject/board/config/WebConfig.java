package com.hbproject.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     *  tomcat Not allowed to load local resource 이슈 해결
     *  특정 url요청시 외부 경로에 있는 리소스에 접근할 수 있도록 설정
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/tomcat/**")
                .addResourceLocations("file:///Users/cjenm/개인폴더/files/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //10MB
        resolver.setMaxUploadSize(1024 * 1024 * 10);
        //2MB
        resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
        //1MB
        resolver.setMaxInMemorySize(1024 * 1024);
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
}
