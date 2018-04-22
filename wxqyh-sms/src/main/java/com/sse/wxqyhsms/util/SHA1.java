package com.sse.wxqyhsms.util;

import com.sse.wxqyhsms.exception.AesException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;

public class SHA1
{

    private static Charset CHARSET = Charset.forName("utf-8");
    private static Base64 base64 = new Base64();
    private static byte[] aesKey = Base64.decodeBase64(WxUtil.EncodingAESKey + "=");
    private static String token = WxUtil.TOKEN;
    private static String corpId = WxUtil.CORPID;
    public static String getString(String token, String timestamp, String nonce, String encrypt)
    {
        String[] array = new String[] {token, timestamp, nonce, encrypt};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(array);

        for(int i = 0; i<array.length; i++)
        {
            sb.append(array[i]);
        }

        String str = sb.toString();
        return getSha1(str);
    }

    public static String getSha1(String str)
    {
        if (str == null || str.length() == 0)
        {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try
        {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    static String decrypt(String text) throws AesException {
        byte[] original;
        try {
            // 设置解密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

            // 使用BASE64对密文进行解码
            byte[] encrypted = Base64.decodeBase64(text);

            // 解密
            original = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.DecryptAESError);
        }

        String xmlContent, from_corpid;
        try {
            // 去除补位字符
            byte[] bytes = PKCS7Encoder.decode(original);

            // 分离16位随机字符串,网络字节序和corpId
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

            int xmlLength = recoverNetworkBytesOrder(networkOrder);

            xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
            from_corpid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
                    CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.IllegalBuffer);
        }

        // corpid不相同的情况
        if (!from_corpid.equals(corpId)) {
            throw new AesException(AesException.ValidateCorpidError);
        }
        return xmlContent;

    }

   static int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }
}
