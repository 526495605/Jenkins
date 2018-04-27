package com.ykyy.server.web;

import com.ykyy.server.bean.Token;
import com.ykyy.server.service.RedisService;
import com.ykyy.server.service.UserService;
import com.ykyy.server.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController
{
    static final int expire = 3600;

    @Autowired
    UserService userService;

//    @Autowired
//    ChildService childService;

    @Autowired
    RedisService redisService;


    public Token createToken()
    {
        String accessKey = Util.genRandom(64);
        String securityKey = Util.genRandom(64);
        return new Token(accessKey, securityKey);
    }

    public Token saveToken(int users_id)
    {
        Token token = createToken();
        redisService.set(token.getAccessKey(), expire, users_id);
        return token;
    }


}
