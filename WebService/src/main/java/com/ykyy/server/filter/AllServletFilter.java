package com.ykyy.server.filter;

import org.jboss.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="ServletFilter",urlPatterns = "/*")
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
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "content-type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");

        String str = response.getHeader("Access-Control-Allow-Origin");
        filterChain.doFilter(servletRequest, response);

//        System.out.println(str + "------------------正在使用过滤器.............."+servletRequest.getLocalAddr());
  //      filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {
        //System.out.println("正在销毁过滤器..............");
        logger.info("正在销毁过滤器..............");
    }
}
