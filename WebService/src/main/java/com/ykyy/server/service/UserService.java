package com.ykyy.server.service;

import com.ykyy.server.bean.UserBean;

public interface UserService
{
    Integer login(String phone, String password);

    UserBean getUserById(int users_id);
}
