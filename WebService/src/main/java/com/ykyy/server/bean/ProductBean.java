package com.ykyy.server.bean;

import java.io.Serializable;

public class ProductBean implements Serializable
{
    private Integer product_id;
    private String product_name;
    private String product_from;
    private String product_to;
    private String product_theme;
    private String product_url;
    private Integer product_money;
    private Long product_time;
    private Integer product_status;

    public Integer getProduct_id()
    {
        return product_id;
    }

    public void setProduct_id(Integer product_id)
    {
        this.product_id = product_id;
    }

    public String getProduct_name()
    {
        return product_name;
    }

    public void setProduct_name(String product_name)
    {
        this.product_name = product_name;
    }

    public String getProduct_from()
    {
        return product_from;
    }

    public void setProduct_from(String product_from)
    {
        this.product_from = product_from;
    }

    public String getProduct_to()
    {
        return product_to;
    }

    public void setProduct_to(String product_to)
    {
        this.product_to = product_to;
    }

    public String getProduct_theme()
    {
        return product_theme;
    }

    public void setProduct_theme(String product_theme)
    {
        this.product_theme = product_theme;
    }

    public String getProduct_url()
    {
        return product_url;
    }

    public void setProduct_url(String product_url)
    {
        this.product_url = product_url;
    }

    public Integer getProduct_money()
    {
        return product_money;
    }

    public void setProduct_money(Integer product_money)
    {
        this.product_money = product_money;
    }

    public Long getProduct_time()
    {
        return product_time;
    }

    public void setProduct_time(Long product_time)
    {
        this.product_time = product_time;
    }

    public Integer getProduct_status()
    {
        return product_status;
    }

    public void setProduct_status(Integer product_status)
    {
        this.product_status = product_status;
    }
}
