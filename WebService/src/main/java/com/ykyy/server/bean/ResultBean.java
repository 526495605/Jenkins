package com.ykyy.server.bean;

import java.io.Serializable;

public class ResultBean implements Serializable
{
    private int code;
    private String message;

    public ResultBean()
    {
    }

    public ResultBean(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
