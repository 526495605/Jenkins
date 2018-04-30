package com.ykyy.server.dao;

import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapping
{

    @Select("SELECT users_id FROM users WHERE users_phone = #{0} and users_password = #{1} and users_status=1")
    Integer login(String phone, String password);

    @Select("SELECT users_id, users_name, users_phone, users_image, users_wx, users_point FROM users  WHERE users_id = #{0} and users_status=1")
    UserBean getUserById(int users_id);

    @Select("SELECT users_name, users_id, users_phone, users_image, users_wx, users_point FROM users  WHERE users_phone = #{0} and users_status=1")
    UserBean getUserByPhone(String users_phone);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    Integer updateUser(UserBean userBean);

    @Update("UPDATE users SET users_phone=#{1} WHERE users_id=#{0} and users_status=1")
    Integer updatePhone(int users_id, String phone);

    @Update("UPDATE users SET users_password=#{2} WHERE users_id=#{0} and users_password=#{1} and users_status=1")
    Integer changePass(int users_id, String oldpass, String newpass);

    @Update("UPDATE users SET users_point=#{1} WHERE users_id=#{0} and users_status=1")
    Integer changePoint(int users_id, int newPoint);

    @InsertProvider(type = UserProvider.class,method = "addUser")
    Integer addUser(UserBean userBean);

    @Delete("UPDATE users SET users_status=0, users_phone = #{1}, users_parent=0 WHERE users_id=#{0} and users_status=1")
    Integer deleteUser(int users_id, String phone);

    @Select("SELECT users_id, child_id, child_name, child_age, child_sex, child_height, child_nation, child_tel, child_grade, child_idcard, child_idcardnum, child_health, child_healthinfo, child_father_name, child_father_tel, child_father_idcard, child_mother_name, child_mother_tel, child_mother_idcard, child_school FROM child WHERE users_id=#{0} and child_status=1")
    List<ChildBean> getChildById(int users_id);
}




















