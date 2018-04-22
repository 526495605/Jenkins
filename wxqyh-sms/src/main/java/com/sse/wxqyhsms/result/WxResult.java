package com.sse.wxqyhsms.result;

import com.sse.wxqyhsms.util.HttpUtil;
import com.sse.wxqyhsms.util.SHA1;
import com.sse.wxqyhsms.util.WxUtil;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WxResult
{
    @RequestMapping(value = "/gettoken", method = RequestMethod.GET)
    public String getResult(@RequestParam(value = "msg_signature") String msg_signature,
                            @RequestParam(value = "timestamp") String timestamp,
                            @RequestParam(value = "nonce") String nonce,
                            @RequestParam(value = "schostr") String schostr) throws Exception
    {
        String _msg_signature = URLDecoder.decode(msg_signature, WxUtil.ENCODE);
        String _timestamp = URLDecoder.decode(timestamp, WxUtil.ENCODE);
        String _nonce = URLDecoder.decode(nonce, WxUtil.ENCODE);
        String _schostr = URLDecoder.decode(schostr, WxUtil.ENCODE);

        System.out.println(msg_signature+ " " + timestamp+ " "+nonce+ " "+ schostr);

        String sign = SHA1.getString(WxUtil.TOKEN, _timestamp, _nonce, _schostr);
        /*
        if(!_msg_signature.equals(sign))
        {
            throw new AesException(AesException.ValidateSignatureError);
        }
        */
        System.out.println("yes");
        String result = "123";
        return result;
    }

    @RequestMapping(value = "/hello")
    public String hello()
    {
        Map<String, Object> map = new HashMap<String ,Object>();
        map.put("name", "owen");
        return HttpUtil.postHttp("http://127.0.0.1:8080/bye", map);
        //return HttpUtil.getHttp("http://127.0.0.1:8080/gettoken?msg_signature=1&timestamp=2&nonce=3&schostr=4");
    }

    @RequestMapping(value = "/bye", method = RequestMethod.POST)
    public String bye(@RequestParam String name)
    {
        return name;
    }

}
