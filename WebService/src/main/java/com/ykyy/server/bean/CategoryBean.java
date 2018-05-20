package com.ykyy.server.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable
{
    private Integer category_id;
    private String category_name;
    private Integer category_status;

    public Integer getCategory_id()
    {
        return category_id;
    }

    public void setCategory_id(Integer category_id)
    {
        this.category_id = category_id;
    }

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String category_name)
    {
        this.category_name = category_name;
    }

    public Integer getCategory_status()
    {
        return category_status;
    }

    public void setCategory_status(Integer category_status)
    {
        this.category_status = category_status;
    }
}
