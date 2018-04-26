package com.ykyy.server.service.serviceimp;

import com.ykyy.server.service.RedisService;
import com.ykyy.server.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.Serializable;
import java.util.List;

@Service
public class RedisServiceImpl<M extends Serializable>  implements RedisService
{

	@Autowired
	RedisTemplate redisTemplate;

	@Override
	public void set(String key, int expire, Object value)
	{
		redisTemplate.opsForValue().set(key, SerializeUtil.serialize(value), expire);
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
