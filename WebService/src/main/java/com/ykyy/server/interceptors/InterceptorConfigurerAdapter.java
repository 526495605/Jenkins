package com.ykyy.server.interceptors;

import com.ykyy.server.interceptors.interceptor.AllInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter
{
    @Bean
    public HandlerInterceptor getAllInterceptor()
    {
        return new AllInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
       // registry.addInterceptor(new AllInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(getAllInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}