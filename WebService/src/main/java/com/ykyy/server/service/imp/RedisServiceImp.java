package com.ykyy.server.service.imp;

import com.mysql.jdbc.TimeUtil;
import com.ykyy.server.service.RedisService;
import com.ykyy.server.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImp<M extends Serializable>  implements RedisService
{

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void set(String key, long expire, Object value)
    {
        redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key)
    {
        byte[] bye = ((String) redisTemplate.opsForValue().get(key)).getBytes();
        return SerializeUtil.unserialize( bye );
    }

}