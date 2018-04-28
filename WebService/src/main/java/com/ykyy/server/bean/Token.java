package com.ykyy.server.bean;
/*
*  "安全认证的token，包含accessKey和securityKey，发送请求时header中需要带上accessKey和sign，"
   + "sign=HTTPMETHOD（GET/POST/PUT/DELETE）+ timestamp（当前UNIX时间戳，有效请求要求
   在10分钟之内）+ sort(params[])（所有请求参数按字母序排序，如a=aval&b=bval...）+securityKey"
*
* */


public class Token
{
    //身份识别码，需要每次请求都带上
    private String accessKey;

    //安全码，每次请求中的sign参数需要使用安全码计算，有效期7天
    private String securityKey;

    private Integer users_id;

    private String users_phone;

    public String getUsers_phone()
    {
        return users_phone;
    }

    public void setUsers_phone(String users_phone)
    {
        this.users_phone = users_phone;
    }

    public Integer getUsers_id()
    {
        return users_id;
    }

    public void setUsers_id(Integer users_id)
    {
        this.users_id = users_id;
    }

    public Token(String accessKey, String securityKey)
    {
        this.accessKey = accessKey;
        this.securityKey = securityKey;
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }

    public String getSecurityKey()
    {
        return securityKey;
    }

    public void setSecurityKey(String securityKey)
    {
        this.securityKey = securityKey;
    }
}
