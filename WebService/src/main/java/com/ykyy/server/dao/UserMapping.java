package com.ykyy.server.dao;

import com.ykyy.server.bean.UserBean;
import com.ykyy.server.provider.UserProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface UserMapping
{

    @Select("SELECT users_id FROM users WHERE users_phone = #{0} and users_password = #{1}")
    Integer login(String phone, String password);

    @Select("SELECT users_name, users_phone, users_image, users_wx, users_date, users_point, users_share FROM users  WHERE users_id = #{0} and users_status=1")
    UserBean getUserById(int users_id);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    Integer updateUser(UserBean userBean);

    @Update("UPDATE users SET users_phone=#{1} WHERE users_id=#{0}")
    Integer updatePhone(int users_id, String phone);

    @Update("UPDATE users SET users_password=#{2} WHERE users_id=#{0} and users_password=#{1}")
    Integer changePass(int users_id, String oldpass, String newpass);

}




















