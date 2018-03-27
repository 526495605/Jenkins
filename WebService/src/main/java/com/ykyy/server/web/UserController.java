package com.ykyy.server.web;

import com.ykyy.server.bean.JsonResult;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user",method = RequestMethod.PUT)
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public ResponseEntity<JsonResult> addUser(@RequestBody UserBean userBean)
    {
        JsonResult r = new JsonResult();
        try
        {
            int reslutId = userService.addUser(userBean);
            if(reslutId<0)
            {
                r.setResult(reslutId);
                r.setStatus("fail");
            }
            else
            {
                r.setResult(reslutId);
                r.setStatus("success");
            }

        }
        catch (Exception e)
        {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }

        return ResponseEntity.ok(r);

    }
}
