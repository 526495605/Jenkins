package com.ykyy.server.service.imp;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.dao.UserMapping;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@CacheConfig(cacheNames = "Users")
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
public class UserServiceImp implements UserService
{

    @Autowired
    UserMapping userMapping;

    @Override
    public UserBean login(String phone, String password)
    {
        Integer result = userMapping.login(phone,password);
        if(result == null || result == 0)
        {
            throw Exceptions.get400Exception("用户不存在");
        }
        return userMapping.getUserByPhone(phone);
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
        Integer result = null;
        if(userBean.getUsers_parent()!=null)
        {
            int parent = userBean.getUsers_parent();
            UserBean user = getUserById(parent);
            if(user == null)
            {
                throw Exceptions.get400Exception("分享码错误");
            }
        }
        try
        {
            result = userMapping.addUser(userBean);
        }
        catch (DataAccessException e)
        {
            final Throwable cause = e.getCause();
            if( cause instanceof MySQLIntegrityConstraintViolationException)
            {
               throw Exceptions.get409Exception("手机号已存在");
            }
        }
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
        Integer result = null;
        try
        {
            result = userMapping.changePass(users_id, oldpass, newpass);
        }
        catch (DataAccessException e)
        {
            log.error(e.getMessage());
            final Throwable cause = e.getCause();
            if( cause instanceof MySQLIntegrityConstraintViolationException)
            {
                throw Exceptions.get400Exception("输入原始密码错误");
            }
        }
        return result;
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
        List<CategoryBean> list = userMapping.getUsersCategory(users_id);
        return list;
    }

    @Override
    public Integer insertUsersCategory(Integer users_id, Integer[] category_id)
    {
        Integer k = 0;
        try
        {
            for(int i = 0; i<category_id.length; i++)
            {
                k += userMapping.insertUsersCategory(users_id, category_id[i]);
            }
        }
        catch (DataAccessException e)
        {
            Throwable throwable = e.getCause();
            if(throwable instanceof MySQLIntegrityConstraintViolationException)
            {
                throw Exceptions.get409Exception("标签id有误");
            }
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

    @Override
    public Integer changeCategoryById(Integer users_id, Integer[] category_ids)
    {
        deleteUsersCategoryaAll(users_id);
        Integer result = insertUsersCategory(users_id, category_ids);
        return result;
    }


}
















