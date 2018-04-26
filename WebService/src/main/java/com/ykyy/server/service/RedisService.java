package com.ykyy.server.service;

import java.io.Serializable;
import java.util.List;

public interface RedisService<M extends Serializable> {
	void set(String key, int expire, Object value);
	void set(String key, Object value);
	Object get(String key);
}
