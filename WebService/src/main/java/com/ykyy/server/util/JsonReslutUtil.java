package com.ykyy.server.util;

import com.ykyy.server.bean.JsonResult;

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
