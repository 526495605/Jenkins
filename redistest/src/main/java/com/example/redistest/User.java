package com.example.redistest;

import java.io.Serializable;

public class User implements Serializable
{
    private int users_id;
    private String users_name;
    private String users_phone;
    private String users_wx;

    public int getUsers_id()
    {
        return users_id;
    }

    public void setUsers_id(int users_id)
    {
        this.users_id = users_id;
    }

    public String getUsers_name()
    {
        return users_name;
    }

    public void setUsers_name(String users_name)
    {
        this.users_name = users_name;
    }

    public String getUsers_phone()
    {
        return users_phone;
    }

    public void setUsers_phone(String users_phone)
    {
        this.users_phone = users_phone;
    }

    public String getUsers_wx()
    {
        return users_wx;
    }

    public void setUsers_wx(String users_wx)
    {
        this.users_wx = users_wx;
    }
}
