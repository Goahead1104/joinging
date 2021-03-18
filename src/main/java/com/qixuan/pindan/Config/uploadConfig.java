package com.qixuan.pindan.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class uploadConfig implements WebMvcConfigurer {
    @Value("${road.avatar}")
    String avatar;

    @Value("${road.avatar_road}")
    String avatar_road;

    @Value("${road.identify}")
    String identify;

    @Value("${road.identify_road}")
    String identify_road;

    @Value("${road.image}")
    String image;

    @Value("${road.image_road}")
    String image_road;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(avatar_road).addResourceLocations(avatar);
        registry.addResourceHandler(identify_road).addResourceLocations(identify);
        registry.addResourceHandler(image_road).addResourceLocations(image);

    }
}
