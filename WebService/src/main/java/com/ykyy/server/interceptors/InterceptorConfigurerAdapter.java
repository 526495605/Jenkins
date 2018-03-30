package com.ykyy.server.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.ykyy.server.interceptors.interceptor.*;

@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
       // registry.addInterceptor(new AllInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new AllInterceptor()).addPathPatterns("/*");
        super.addInterceptors(registry);
    }
}