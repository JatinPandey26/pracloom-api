package com.pracloomcompany.pracloom.Config;

import com.pracloomcompany.pracloom.Interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TenantConfig implements WebMvcConfigurer {

    @Value("${Client.origin}")
    private String orgin;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new RequestInterceptor());
    }

    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(orgin).allowedMethods("*").allowedHeaders("Authorization", "Content-Type","X-TenantID")
                .allowCredentials(true);
    }
}
