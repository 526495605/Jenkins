package com.ykyy.server.interceptors.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AllInterceptor implements HandlerInterceptor
{

    @Value("${server.debug}")
    public boolean debug;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
 //       System.out.println("拦截器MyInterceptor------->3、请求结束之后被调用，主要用于清理工作。");

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
 //       System.out.println("拦截器MyInterceptor------->2、请求之后调用，在视图渲染之前，也就是Controller方法调用之后");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
 //       System.out.println("拦截器MyInterceptor------->1、请求之前调用，也就是Controller方法调用之前。");

        if(request.getRequestURI().equals("/server/swagger-ui.html") || request.getRequestURL().equals("/server/user/login"))
        {
            return true;
        }
        else
        {
            log.info("token = " + request.getHeader("X-YAuch-Token"));
        }
        return true;//返回true则继续向下执行，返回false则取消当前请求
    }

}
