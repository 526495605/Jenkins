package com.ykyy.server.service.imp;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.dao.UserMapping;
import com.ykyy.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "Users")
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
public class UserServiceImp implements UserService
{

    @Autowired
    UserMapping userMapping;

    @Override
    public UserBean login(String phone, String password)
    {

        int id = userMapping.login(phone, password);
        return userMapping.getUserById(id);
    }

    @Override
    public UserBean getUserById(int users_id)
    {
        return userMapping.getUserById(users_id);
    }

    @Override
    public UserBean getUserByPhone(String users_phone)
    {
        return userMapping.getUserByPhone(users_phone);
    }

    @Override
    public Integer addUser(UserBean userBean)
    {
        int result = userMapping.addUser(userBean);
        return result;
    }

    @Override
    public UserBean updateUser(UserBean userBean)
    {
        userMapping.updateUser(userBean);
        return getUserById(userBean.getUsers_id());
    }

    @Override
    public Integer changePass(int users_id, String oldpass, String newpass)
    {
        return userMapping.changePass(users_id, oldpass, newpass);
    }

    @Override
    public Integer changePoint(int users_id, int newPoint)
    {
        return userMapping.changePoint(users_id, newPoint);
    }

    @Override
    public Integer updatePhone(int users_id, String phone)
    {
        return userMapping.updatePhone(users_id, phone);
    }

    @Override
    public Integer deleteUser(int users_id, String users_phone)
    {
        return userMapping.deleteUser(users_id, users_phone);
    }

    @Override
    public List<ChildBean> getChildById(int user_id)
    {
        return userMapping.getChildById(user_id);
    }



    @Override
    public List<CategoryBean> getUsersCategory(Integer users_id)
    {
        return userMapping.getUsersCategory(users_id);
    }

    @Override
    public Integer insertUsersCategory(Integer users_id, Integer[] category_id)
    {
        int k = 0;
        for(int i = 0; i<category_id.length; i++)
        {
            k += userMapping.insertUsersCategory(users_id, category_id[i]);
        }
        return k;
    }

    @Override
    public Integer deleteUsersCategoryaAll(Integer users_id)
    {
        return userMapping.deleteUsersCategoryaAll(users_id);
    }

    @Override
    public Integer deleteUsersCategoryById(Integer id)
    {
        return userMapping.deleteUsersCategoryById(id);
    }


}
















