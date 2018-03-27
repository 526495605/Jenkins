package com.ykyy.server.service.serviceimp;

import com.ykyy.server.bean.UserBean;
import com.ykyy.server.dao.UserMapping;
import com.ykyy.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
    private UserMapping userMapping;

    public void setUserMapping(UserMapping userMapping)
    {
        this.userMapping = userMapping;
    }


    @Override
    public int addUser(UserBean userBean)
    {
        return userMapping.add(userBean);
    }

    @Override
    public UserBean getUser(int id)
    {

        return userMapping.get(id);
    }

    @Override
    public int deleteUser(UserBean userBean)
    {
        return userMapping.delete(userBean);
    }

    @Override
    public int updateUser(UserBean userBean)
    {
        return userMapping.update(userBean);
    }

    @Override
    public List<UserBean> getALl()
    {
        return userMapping.getAll();
    }

    @Override
    public List<UserBean> getUserPage(int begin)
    {
        return userMapping.getUserPage(begin);
    }


}
