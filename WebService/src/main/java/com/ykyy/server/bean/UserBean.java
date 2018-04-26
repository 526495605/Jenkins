package com.ykyy.server.bean;

import java.io.Serializable;
import java.sql.Date;

public class UserBean implements Serializable
{
    private static final long serialVersionUID = 100000000L;

    private int users_id ;
    private String users_name;
    private String users_phone;
    private String users_password;
    private String users_image;
    private String users_wx;
    private Date users_data;
    private String users_hobby;
    private int users_point;
    private int users_share;
    private int users_parent;
    private int users_fan;
    private int users_status;

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

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

    public String getUsers_password()
    {
        return users_password;
    }

    public void setUsers_password(String users_password)
    {
        this.users_password = users_password;
    }

    public String getUsers_image()
    {
        return users_image;
    }

    public void setUsers_image(String users_image)
    {
        this.users_image = users_image;
    }

    public String getUsers_wx()
    {
        return users_wx;
    }

    public void setUsers_wx(String users_wx)
    {
        this.users_wx = users_wx;
    }

    public Date getUsers_data()
    {
        return users_data;
    }

    public void setUsers_data(Date users_data)
    {
        this.users_data = users_data;
    }

    public String getUsers_hobby()
    {
        return users_hobby;
    }

    public void setUsers_hobby(String users_hobby)
    {
        this.users_hobby = users_hobby;
    }

    public int getUsers_point()
    {
        return users_point;
    }

    public void setUsers_point(int users_point)
    {
        this.users_point = users_point;
    }

    public int getUsers_share()
    {
        return users_share;
    }

    public void setUsers_share(int users_share)
    {
        this.users_share = users_share;
    }

    public int getUsers_parent()
    {
        return users_parent;
    }

    public void setUsers_parent(int users_parent)
    {
        this.users_parent = users_parent;
    }

    public int getUsers_fan()
    {
        return users_fan;
    }

    public void setUsers_fan(int users_fan)
    {
        this.users_fan = users_fan;
    }

    public int getUsers_status()
    {
        return users_status;
    }

    public void setUsers_status(int users_status)
    {
        this.users_status = users_status;
    }
}
