package com.ykyy.server.service.imp;

import com.ykyy.server.dao.UserMapping;
import com.ykyy.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@CacheConfig(cacheNames = "Users")
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
public class UserServiceImp implements UserService
{

    @Autowired
    UserMapping userMapping;

    @Override
    public Integer login(String phone, String password)
    {

        return userMapping.login(phone, password);
    }
}
