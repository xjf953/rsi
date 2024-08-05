package com.shawn.votesystem.config;

import com.shawn.votesystem.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Value("${vote.profile}")
    private String baseUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        registry.addResourceHandler("/vote/base/banner/**").addResourceLocations("file:" + UploadUtil.getRootPath() + baseUrl);
    }
}
