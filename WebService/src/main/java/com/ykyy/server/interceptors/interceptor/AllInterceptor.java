package com.ykyy.server.interceptors.interceptor;

import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.RedisService;
import com.ykyy.server.util.ApplicationUtil;
import com.ykyy.server.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
@Component
public class AllInterceptor implements HandlerInterceptor
{

    @Value("${server.debug}")
    private boolean debug;

    @Autowired
    private RedisService redisService;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception
    {
        //       System.out.println("拦截器MyInterceptor------->3、请求结束之后被调用，主要用于清理工作。");

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception
    {
        //       System.out.println("拦截器MyInterceptor------->2、请求之后调用，在视图渲染之前，也就是Controller方法调用之后");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
    {

        if (debug)
        {
            return true;
        }
        if (request.getRequestURI().equals("/server/swagger-ui.html") || request.getRequestURI().equals("/server/user/login") || request.getRequestURI().equals("/server/user/add"))
        {
            return true;
        } else
        {
            //返回的结果，userid，随机字符串1，随即字符串2
            log.info("token = " + request.getHeader("X-YAuch-Token"));
            String result = request.getHeader("X-YAuch-Token");
            if(result == null || result ==",")
            {
                throw Exceptions.get412Exception("token不存在");
            }
            String[] strs = result.split(",");
            String output = strs[0];
            String accessKey = strs[1];
            String str1 = strs[2];
            String str2 = strs[3];

            String secruityKey = (String) redisService.get(accessKey);
            if (secruityKey == null)
            {
                throw Exceptions.get412Exception("token不z存在");
            }

            strs = new String[]{secruityKey, str1, str2};
            Arrays.sort(strs);

            StringBuffer stringBuffer = new StringBuffer(strs[0]);
            for(int i = 1; i<3; i++)
            {
                stringBuffer.append(strs[i]);
            }
            String str = MD5Util.MD5(stringBuffer.toString());
            if(!str.equals(output))
            {
                System.out.println("token验证失败");
                throw Exceptions.get412Exception("token验证失败");
            }
             redisService.set(accessKey, ApplicationUtil.EXPIRE, secruityKey);
        }
        return true;//返回true则继续向下执行，返回false则取消当前请求
    }

}
