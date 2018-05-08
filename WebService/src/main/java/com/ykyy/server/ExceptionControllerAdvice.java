package com.ykyy.server;

import com.ykyy.server.exception.Exceptions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice
{
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("message", ex.getMessage());
        return map;
    }
    @ResponseBody
    @ExceptionHandler(value = Exceptions.ApiException.class)
    public Map myErrorHandler( Exceptions.ApiException ex) {
        Map map = new HashMap();
        map.put("code", ex.getStatsCode());
        map.put("message", ex.getMessage());
        return map;
    }



}
