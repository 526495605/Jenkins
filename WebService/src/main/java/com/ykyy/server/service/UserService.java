package com.ykyy.server.service;

import com.ykyy.server.bean.UserBean;

public interface UserService
{
    public int addUser(UserBean userBean);

    public UserBean getUser(int id);
}
