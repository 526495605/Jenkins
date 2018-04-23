package com.ykyy.server.dao;

import com.ykyy.server.bean.UserBean;
import com.ykyy.server.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserMapping
{

    @Insert("Insert into users(users_name, users_password, users_phone, users_wx, users_date) value (#{users_name} ,#{users_password}, #{users_phone}, #{users_wx}, now())")
    int add(UserBean userBean);

    @Update("UPDATE users SET users_status=0 WHERE users_id=#{users_id}")
    int delete(int users_id);

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users WHERE users_status=1")
    List<UserBean> getAll();

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users WHERE users_status=1 limit #{begin},10")
    List<UserBean> getUserPage(int begin);

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users where users_id=#{id} and users_status=1")
    UserBean get(int id);

    @SelectProvider(type=UserProvider.class,method="login")
    int login(UserBean user);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    int update(UserBean userBean);
}
