package com.ykyy.server.service.serviceimp;

import com.ykyy.server.bean.UserBean;
import com.ykyy.server.dao.UserMapping;
import com.ykyy.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
    private UserMapping userMapping;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

//    @Resource(name = "redisTemplate")
//    ValueOperations<Object, Object> valOps;

    public void setUserMapping(UserMapping userMapping)
    {
        this.userMapping = userMapping;
    }



    /**
    * @Author:owen
    * @Description: 添加用户
    * @Date:Create in 15:30 2018/4/3
    * @Modified By:
    */
    @Override
    public int addUser(UserBean userBean)
    {
        return userMapping.add(userBean);
    }

    /**
    * @Author:owen
    * @Description: 通过id查找用户
    * @Date:Create in 15:30 2018/4/3
    * @Modified By:
    */
    @Override
    public UserBean getUser(int id)
    {
        /*
        UserBean userBean = (UserBean) valOps.get(id);
        if(userBean == null)
        {
            userBean = userMapping.get(id);
            valOps.set(userBean.getId(), userBean);

            System.out.println("null++++++++++++++++++++++++++++++++++++++");
        }
        */
        UserBean userBean = (UserBean) redisTemplate.opsForValue().get(id);
        if (userBean == null)
        {
            userBean = userMapping.get(id);
            redisTemplate.opsForValue().set(userBean.getUsers_id(), userBean, 5, TimeUnit.SECONDS);
            //redisTemplate.expire(userBean.getId(), 10, TimeUnit.SECONDS);

            System.out.println("null++++++++++++++++++++++++++++++++++++++");
        }
        else
        {
            System.out.println("++++++++++++++++++++++++++redis");
        }
        return userBean;
    }

    /**
    * @Author:owen
    * @Description: 删除用户
    * @Date:Create in 15:31 2018/4/3
    * @Modified By:
    */
    @Override
    public int deleteUser(UserBean userBean)
    {
        return userMapping.delete(userBean);
    }

    /**
    * @Author:owen
    * @Description:更新用户
    * @Date:Create in 15:31 2018/4/3
    * @Modified By:
    */
    @Override
    public int updateUser(UserBean userBean)
    {
        return userMapping.update(userBean);
    }

    /**
    * @Author:owen
    * @Description: 获取全部的用户
    * @Date:Create in 15:31 2018/4/3
    * @Modified By:
    */
    @Override
    public List<UserBean> getALl()
    {
        return userMapping.getAll();
    }

    /**
    * @Author:owen
    * @Description:分页查找
    * @Date:Create in 15:33 2018/4/3
    * @Modified By:
    */
    @Override
    public List<UserBean> getUserPage(int begin)
    {
        return userMapping.getUserPage(begin);
    }

    @Override
    public int login(UserBean userBean)
    {
        return userMapping.login(userBean);
    }
}
