package com.sse.wxqyhsms.result;

import com.sse.wxqyhsms.exception.AesException;
import com.sse.wxqyhsms.util.SHA1;
import com.sse.wxqyhsms.util.WxUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
public class WxResult
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getResult(@RequestParam(value = "msg_signature") String msg_signature,
                            @RequestParam(value = "timestamp") String timestamp,
                            @RequestParam(value = "nonce") String nonce,
                            @RequestParam(value = "schostr") String schostr) throws Exception
    {
        String _msg_signature = URLDecoder.decode(msg_signature, WxUtil.ENCODE);
        String _timestamp = URLDecoder.decode(timestamp, WxUtil.ENCODE);
        String _nonce = URLDecoder.decode(nonce, WxUtil.ENCODE);
        String _schostr = URLDecoder.decode(schostr, WxUtil.ENCODE);

        String sign = SHA1.getSHA1(WxUtil.TOKEN, _timestamp, _nonce, _schostr);

        if(!_msg_signature.equals(sign))
        {
            throw new AesException(AesException.ValidateSignatureError);
        }
        String result = SHA1.decrypt(_schostr);
        return result;


    }

    @RequestMapping(value = "/hello")
    public String hello()
    {
        return "hello world";
    }
}
