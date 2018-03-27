package com.ykyy.server.dao;

import com.ykyy.server.bean.UserBean;
import com.ykyy.server.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface UserMapping
{

    @Insert("Insert into users(users_name, users_password, users_phone, users_wx, users_date) value (#{name} ,#{password}, #{phone}, #{wx}, now())")
    public int add(UserBean userBean);

    @DeleteProvider(type=UserProvider.class,method="delete")
    public int delete(UserBean userBean);

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users")
    @Results({
            @Result(property = "id", column ="users_id"),
            @Result(property = "name", column ="users_name"),
            @Result(property = "phone", column ="users_phone"),
            @Result(property = "wx", column ="users_wx"),
            @Result(property = "date", column ="users_date")
    })
    public List<UserBean> getAll();

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users limit #{begin},10")
    @Results({
            @Result(property = "id", column ="users_id"),
            @Result(property = "name", column ="users_name"),
            @Result(property = "phone", column ="users_phone"),
            @Result(property = "wx", column ="users_wx"),
            @Result(property = "date", column ="users_date")
    })
    public List<UserBean> getUserPage(int begin);

    @Select("select users_id, users_name, users_phone, users_wx, users_date from users where users_id=#{id}")
    @Results({
            @Result(property = "id", column ="users_id"),
            @Result(property = "name", column ="users_name"),
            @Result(property = "phone", column ="users_phone"),
            @Result(property = "wx", column ="users_wx"),
            @Result(property = "date", column ="users_date")
    })
    public UserBean get(int id);


    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    public int update(UserBean userBean);
}
