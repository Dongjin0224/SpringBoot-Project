package com.example.test.config;

import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/webfonts/**").addResourceLocations("classpath:/static/webfonts/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///C:/upload/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/hosupload/**").addResourceLocations("file:///C:/hosupload/").setCachePeriod(60 * 60 * 24 * 365);
    }


}