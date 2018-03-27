package com.ykyy.server.service;

import com.ykyy.server.bean.UserBean;

import java.util.List;

public interface UserService
{
    public int addUser(UserBean userBean);

    public UserBean getUser(int id);

    public int deleteUser(UserBean userBean);

    public int updateUser(UserBean userBean);

    public List<UserBean> getALl();

    public List<UserBean> getUserPage(int begin);
}
