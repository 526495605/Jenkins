package com.zuul.demo;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class MyFilter extends ZuulFilter
{
    @Override
    public String filterType()
    {
        return "pre";
    }

    @Override
    public int filterOrder()
    {
        return 0;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run()
    {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        Object accessToken = httpServletRequest.getParameter("token");
//        if(accessToken==null)
//        {
//            log.warn("taken is null");
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(401);
//            try
//            {
//                requestContext.getResponse().getWriter().write("toke is null");
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//                return null;
//            }
//
//        }
        log.info("ok");
        return null;
    }
}
