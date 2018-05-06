package com.ykyy.server.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserBean implements Serializable
{
    private static final long serialVersionUID = 100000000L;

    private Integer users_id ;
    private String users_name;
    private String users_phone;
    private String users_password;
    private String users_image;
    private String users_wx;
    private Long users_data;
    private Integer users_point;
    private Integer users_parent;
    private Integer users_status;
    private List<ChildBean> list = new ArrayList<ChildBean>();


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

    public Long getUsers_data()
    {
        return users_data;
    }

    public void setUsers_data(Long users_data)
    {
        this.users_data = users_data;
    }

    public Integer getUsers_point()
    {
        return users_point;
    }

    public void setUsers_point(Integer users_point)
    {
        this.users_point = users_point;
    }


    public Integer getUsers_parent()
    {
        return users_parent;
    }

    public void setUsers_parent(Integer users_parent)
    {
        this.users_parent = users_parent;
    }

    public Integer getUsers_status()
    {
        return users_status;
    }

    public void setUsers_status(Integer users_status)
    {
        this.users_status = users_status;
    }

    public List<ChildBean> getList()
    {
        return list;
    }

    public void setList(List<ChildBean> list)
    {
        this.list = list;
    }
}
