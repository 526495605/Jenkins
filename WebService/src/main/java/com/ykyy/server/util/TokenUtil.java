package com.ykyy.server.util;

import sun.misc.BASE64Encoder;
import java.util.Random;

public class TokenUtil
{

    public static String getToken()
    {
        Random random = new Random();
        byte[] tokenData = new byte[8];
        random.nextBytes(tokenData);
        BASE64Encoder encoder = new BASE64Encoder();
        String token = encoder.encode(tokenData);
        return token;
    }




}
