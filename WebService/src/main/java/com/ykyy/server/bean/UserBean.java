package com.ykyy.server.bean;

import java.sql.Date;

public class UserBean
{
    private Integer id;
    private String name;
    private String phone;
    private Date date;
    private String wx;
    private String password;

    public UserBean()
    {
    }

    public UserBean(Integer id, String name, String phone, Date date, String wx, String password)
    {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.wx = wx;
        this.password = password;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getWx()
    {
        return wx;
    }

    public void setWx(String wx)
    {
        this.wx = wx;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                ", wx='" + wx + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
