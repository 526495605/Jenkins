package com.example.redistest;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface UserMapping
{
    @Insert("insert users(users_id, users_name, users_wx) values(#{users_id},#{users_name},#{users_wx})")
    int insert(User user);

    @Update("Update users SET users_name=#{users_name} Where users_id=#{users_id}")
    int update(User user);

    @Delete("DELETE from users where users_id=#{id}")
    int delete(int id);

    @Select("SELECT * from users where users_id=#{id}")
    User find(int id);

}
