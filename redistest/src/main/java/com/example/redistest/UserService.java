package com.example.redistest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "usercachessss")
@Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = Exception.class)
public class UserService
{
    @Autowired
    UserMapping userMapping;

    @CachePut(key = "#p0.users_id")
    public User insert(User user)
    {
        userMapping.insert(user);
        return userMapping.find(user.getUsers_id());
    }

    @CachePut(key = "#p0.users_id")
    public User update(User user)
    {
        userMapping.update(user);
        return userMapping.find(user.getUsers_id());
    }


    @CacheEvict(key = "#p0")
    public int delete(int id)
    {

        return userMapping.delete(id);
    }

    @Cacheable(key = "#p0")
    public User find(int id)
    {
        return userMapping.find(id);
    }
}
