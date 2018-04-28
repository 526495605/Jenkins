package com.ykyy.server.service;

import com.ykyy.server.bean.UserBean;

public interface UserService
{
    /**
    * @Author:owen
    * @Description:登录
    * @Date:Create in 9:36 2018/4/28
    * @Modified By:
    */
    UserBean login(String phone, String password);


    /**
    * @Author:owen
    * @Description:通过ID查找用户
    * @Date:Create in 9:37 2018/4/28
    * @Modified By:
    */
    UserBean getUserById(int users_id);

    /**
     * @Author:owen
     * @Description:通过phone查找用户
     * @Date:Create in 9:37 2018/4/28
     * @Modified By:
     */
    UserBean getUserByPhone(String phone);

    /**
    * @Author:owen
    * @Description:添加用户
    * @Date:Create in 9:37 2018/4/28
    * @Modified By:
    */
    Integer addUser(UserBean userBean);


    /**
    * @Author:owen
    * @Description:更新用户信息
    * @Date:Create in 9:37 2018/4/28
    * @Modified By:
    */
    UserBean updateUser(UserBean userBean);

    /**
    * @Author:owen
    * @Description:修改密码
    * @Date:Create in 9:37 2018/4/28
    * @Modified By:
    */
    Integer changePass(int users_id, String oldpass, String newpass);


    /**
    * @Author:owen
    * @Description:修改积分
    * @Date:Create in 9:37 2018/4/28
    * @Modified By:
    */
    Integer changePoint(int users_id, int newPoint);


    /**
    * @Author:owen
    * @Description:更新手机号码
    * @Date:Create in 9:37 2018/4/28
    * @Modified By:
    */
    Integer updatePhone(int users_id, String phone);

    /**
    * @Author:owen
    * @Description:删除用户
    * @Date:Create in 13:31 2018/4/28
    * @Modified By:
    */
    Integer deleteUser(int users_id, String users_phone);
}

