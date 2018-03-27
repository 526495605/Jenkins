package com.ykyy.server.dao;

import com.ykyy.server.bean.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapping
{

    @Insert("Insert into users(users_name, users_password, users_phone, users_wx, users_date) value (#{name} ,#{password}, #{phone}, #{wx}, now())")
    public int add(UserBean userBean);

    public int delete(UserBean userBean);

    public List<UserBean> getAll();

    public List<UserBean> getUserPage(int begin);

    @Select("select users_id, users_name, users_phone, users_wx from users where users_id=#{id}")
    public UserBean get(int id);

    public int update(UserBean userBean);
}
