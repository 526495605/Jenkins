package com.sse.wxqyhsms.util;

import com.sse.wxqyhsms.exception.AesException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA1
{
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt)
    {
        String[] array = new String[] {token, timestamp, nonce, encrypt};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(array);

        for(int i = 0; i<array.length; i++)
        {
            sb.append(array[i]);
        }

        String str = sb.toString();

        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for(int i = 0;i<digest.length; i++)
            {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if(shaHex.length()<2)
                    hexstr.append(0);

                hexstr.append(shaHex);
            }
            return hexstr.toString();

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String text) throws AesException
    {
        return null;
    }
}
