package com.ykyy.server.filter;

import org.jboss.logging.Logger;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter(filterName="ServletFilter",urlPatterns = "/*")
public class AllServletFilter implements Filter
{

    private Logger logger = Logger.getLogger(AllServletFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        logger.info("正在初始化用过滤器..............");
       // System.out.println("正在初始化用过滤器..............");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        //logger.info("正在使用过滤器..............");
        System.out.println("正在使用过滤器.............."+servletRequest.getLocalAddr());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {
        //System.out.println("正在销毁过滤器..............");
        logger.info("正在销毁过滤器..............");
    }
}
