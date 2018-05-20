package com.ykyy.server.bean;

import java.io.Serializable;

public class UsersCategoryBean implements Serializable
{
    private Integer users_category_id;
    private Integer users_id;
    private Integer category_id;
    private Integer getUsers_category_status;

    public Integer getUsers_category_id()
    {
        return users_category_id;
    }

    public void setUsers_category_id(Integer users_category_id)
    {
        this.users_category_id = users_category_id;
    }

    public Integer getUsers_id()
    {
        return users_id;
    }

    public void setUsers_id(Integer users_id)
    {
        this.users_id = users_id;
    }

    public Integer getCategory_id()
    {
        return category_id;
    }

    public void setCategory_id(Integer category_id)
    {
        this.category_id = category_id;
    }

    public Integer getGetUsers_category_status()
    {
        return getUsers_category_status;
    }

    public void setGetUsers_category_status(Integer getUsers_category_status)
    {
        this.getUsers_category_status = getUsers_category_status;
    }
}
