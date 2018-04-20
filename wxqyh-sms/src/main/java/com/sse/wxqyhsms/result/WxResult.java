package com.sse.wxqyhsms.result;

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
                            @RequestParam(value = "schostr") String schostr) throws UnsupportedEncodingException
    {
        String _msg_signature = URLDecoder.decode(msg_signature, WxUtil.ENCODE);
        String _timestamp = URLDecoder.decode(timestamp, WxUtil.ENCODE);
        String _nonce = URLDecoder.decode(nonce, WxUtil.ENCODE);
        String _schostr = URLDecoder.decode(schostr, WxUtil.ENCODE);

        String[] array = new String[]{_timestamp, WxUtil.TOKEN, _nonce};

        StringBuilder sbuilder = new StringBuilder();
        for (String str : array) {
            sbuilder.append(str);
        }

        String token = sbuilder.toString();
        String mytoken = Base64.encodeBase64(token.getBytes()).toString();

        //校验签名
        if (mytoken != null && mytoken != "" && mytoken.equals(_msg_signature))
        {
            System.out.println("签名校验通过。");
            //String msg = Base64.decodeBase64(_schostr);



            return _schostr; //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
        }
        else
        {
            System.out.println("签名校验失败。");
            return null;
        }
    }

    @RequestMapping(value = "/hello")
    public String hello()
    {
        return "hello world";
    }
}
