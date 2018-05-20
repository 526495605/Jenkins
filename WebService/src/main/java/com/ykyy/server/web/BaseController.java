package com.ykyy.server.web;

import com.ykyy.server.bean.Token;
import com.ykyy.server.service.*;
import com.ykyy.server.util.ApplicationUtil;
import com.ykyy.server.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController
{
    @Autowired
    UserService userService;

    @Autowired
    ChildService childService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    RedisService redisService;

    @Autowired
    PurchaseService purchaseService;

    public Token createToken()
    {
        String accessKey = Util.genRandom(64);
        String securityKey = Util.genRandom(64);
        return new Token(accessKey, securityKey);
    }

    public Token saveToken(int users_id)
    {
        Token token = createToken();
        redisService.set(token.getAccessKey(), ApplicationUtil.EXPIRE, users_id);
        return token;
    }


}
