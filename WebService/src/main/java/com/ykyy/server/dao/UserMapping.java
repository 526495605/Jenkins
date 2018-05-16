package com.ykyy.server.dao;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserMapping
{

    @Select("SELECT users_id FROM users WHERE users_phone = #{0} and users_password = #{1} and users_status=1")
    Integer login(String phone, String password) throws DataAccessException;

    @Select("SELECT users_id, users_name, users_phone, users_image, users_wx, users_point FROM users  WHERE users_id = #{0} and users_status=1")
    @Result(column="users_id",property="list", many=@Many(select = "com.ykyy.server.dao.UserMapping.getChildById",fetchType=FetchType.LAZY))
    UserBean getUserById(int users_id);

    @Select("SELECT users_name, users_id, users_phone, users_image, users_wx, users_point FROM users  WHERE users_phone = #{0} and users_status=1")
    UserBean getUserByPhone(String users_phone);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    Integer updateUser(UserBean userBean);

    @Update("UPDATE users SET users_phone=#{1} WHERE users_id=#{0} and users_status=1")
    Integer updatePhone(int users_id, String phone);

    @Update("UPDATE users SET users_password=#{2} WHERE users_id=#{0} and users_password=#{1} and users_status=1")
    Integer changePass(int users_id, String oldpass, String newpass) throws DataAccessException;

    @Update("UPDATE users SET users_point=#{1} WHERE users_id=#{0} and users_status=1")
    Integer changePoint(int users_id, int newPoint);

    @InsertProvider(type = UserProvider.class,method = "addUser")
    Integer addUser(UserBean userBean) throws DataAccessException;

    @Delete("UPDATE users SET users_status=0, users_phone = #{1}, users_parent=0 WHERE users_id=#{0} and users_status=1")
    Integer deleteUser(int users_id, String phone);

    @Select("SELECT users_id, child_id, child_name, child_age, child_sex, child_height, child_nation, child_tel, child_grade, child_idcard, child_idcardnum, child_health, child_healthinfo, child_father_name, child_father_tel, child_father_idcard, child_mother_name, child_mother_tel, child_mother_idcard, child_school FROM child WHERE users_id=#{0} and child_status=1")
    @Results(
            {
                    @Result(column = "child_id", property = "child_id", id = true),
                    @Result(column = "child_id", property = "categoryBeans", many=@Many(select = "com.ykyy.server.dao.ChildMapping.getChildCategory", fetchType = FetchType.LAZY))
            }
    )
    List<ChildBean> getChildById(int users_id);


    //------------------------------------------------------------------------------------

    @Select("SELECT category.category_id, category_name FROM category, users_category WHERE category.category_id=users_category.category_id and users_id = #{0}")
    List<CategoryBean> getUsersCategory(Integer users_id);

    @Insert("INSERT users_category (users_id, category_id) VALUE (#{0}, #{1})")
    Integer insertUsersCategory(Integer users_id, Integer category_id) throws DataAccessException;

    @Delete("Delete FROM users_category WHERE users_id=#{0}")
    Integer deleteUsersCategoryaAll(Integer users_id);

    @Delete("Delete FROM users_category WHERE users_category_id=#{0}")
    Integer deleteUsersCategoryById(Integer id);
}




















