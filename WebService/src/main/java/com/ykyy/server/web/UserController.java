package com.ykyy.server.web;

import com.ykyy.server.bean.JsonResult;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value="/add",method = RequestMethod.PUT)
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

    @RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserById(@PathVariable(value = "id") int id)
    {
        JsonResult r = new JsonResult();
        try
        {
            UserBean user = userService.getUser(id);
            //System.out.println(id);
            r.setResult(user);
            r.setStatus("ok");
        } catch (Exception e)
        {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value="/deleteuser", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> deleteUser(@RequestBody UserBean userBean)
    {
        JsonResult r = new JsonResult();
        try
        {
            int result = userService.deleteUser(userBean);
            if(result<0)
            {
                r.setResult(result);
                r.setStatus("fail");
            }
            else
            {
                r.setResult(result);
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
