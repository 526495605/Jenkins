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
    private int users_point;
    private int users_share;
    private int users_parent;
    private int users_status;

}
