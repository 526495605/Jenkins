package com.ykyy.server.bean;

import java.io.Serializable;
import java.sql.Date;

public class UserBean implements Serializable
{
    private static final long serialVersionUID = -1L;

    private Integer users_id;
    private String users_name;
    private String users_phone;
    private Date users_date;
    private String users_wx;
    private String users_password;

    private int users_status;

    public int getUsers_status()
    {
        return users_status;
    }

    public void setUsers_status(int users_status)
    {
        this.users_status = users_status;
    }

    public UserBean()
    {
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public Integer getUsers_id()
    {
        return users_id;
    }

    public void setUsers_id(Integer users_id)
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

    public Date getUsers_date()
    {
        return users_date;
    }

    public void setUsers_date(Date users_date)
    {
        this.users_date = users_date;
    }

    public String getUsers_wx()
    {
        return users_wx;
    }

    public void setUsers_wx(String users_wx)
    {
        this.users_wx = users_wx;
    }

    public String getUsers_password()
    {
        return users_password;
    }

    public void setUsers_password(String users_password)
    {
        this.users_password = users_password;
    }
}
