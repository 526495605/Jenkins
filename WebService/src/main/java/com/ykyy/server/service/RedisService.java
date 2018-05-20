package com.ykyy.server.service;

import java.io.Serializable;

public interface RedisService<M extends Serializable> {
    void set(String key, long expire, Object value);
    void set(String key, Object value);
    Object get(String key);
}