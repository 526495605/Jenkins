package com.ykyy.server.dao;

import com.ykyy.server.bean.UserBean;
import com.ykyy.server.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapping
{

    @Insert("Insert into users(users_name, users_password, users_phone, users_wx, users_date) value (#{name} ,#{password}, #{phone}, #{wx}, now())")
    int add(UserBean userBean);

    @DeleteProvider(type=UserProvider.class,method="delete")
    int delete(UserBean userBean);

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users")
    @Results({
            @Result(property = "id", column ="users_id"),
            @Result(property = "name", column ="users_name"),
            @Result(property = "phone", column ="users_phone"),
            @Result(property = "wx", column ="users_wx"),
            @Result(property = "date", column ="users_date")
    })
    List<UserBean> getAll();

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users limit #{begin},10")
    @Results({
            @Result(property = "id", column ="users_id"),
            @Result(property = "name", column ="users_name"),
            @Result(property = "phone", column ="users_phone"),
            @Result(property = "wx", column ="users_wx"),
            @Result(property = "date", column ="users_date")
    })
    List<UserBean> getUserPage(int begin);

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users where users_id=#{id}")
   @Results({
            @Result(property = "id", column ="users_id"),
            @Result(property = "name", column ="users_name"),
            @Result(property = "phone", column ="users_phone"),
            @Result(property = "wx", column ="users_wx"),
            @Result(property = "date", column ="users_date")

    })
    UserBean get(int id);

    @SelectProvider(type=UserProvider.class,method="login")
    int login(UserBean user);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    int update(UserBean userBean);
}
