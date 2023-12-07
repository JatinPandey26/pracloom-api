package com.pracloomcompany.pracloom.Config;

import com.pracloomcompany.pracloom.Interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TenantConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new RequestInterceptor());
    }
}
