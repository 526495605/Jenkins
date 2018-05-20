package com.ykyy.server.bean;

import java.io.Serializable;

public class ChildCategoryBean implements Serializable
{
    private Integer child_category_id;
    private Integer child_id;
    private Integer category_id;
    private Integer child_category_status;

    public Integer getChild_category_id()
    {
        return child_category_id;
    }

    public void setChild_category_id(Integer child_category_id)
    {
        this.child_category_id = child_category_id;
    }

    public Integer getChild_id()
    {
        return child_id;
    }

    public void setChild_id(Integer child_id)
    {
        this.child_id = child_id;
    }

    public Integer getCategory_id()
    {
        return category_id;
    }

    public void setCategory_id(Integer category_id)
    {
        this.category_id = category_id;
    }

    public Integer getChild_category_status()
    {
        return child_category_status;
    }

    public void setChild_category_status(Integer child_category_status)
    {
        this.child_category_status = child_category_status;
    }
}
