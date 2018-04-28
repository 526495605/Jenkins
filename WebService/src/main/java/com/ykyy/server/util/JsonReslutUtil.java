package com.ykyy.server.util;

public class JsonReslutUtil
{
    public static void getJsonResult(int result, JsonResult r)
    {
        if(result<0)
        {
            r.setResult(result);
            r.setStatus("fail");
        }
        else
        {
            r.setResult(result);
            r.setStatus("success");
        }
    }
}
