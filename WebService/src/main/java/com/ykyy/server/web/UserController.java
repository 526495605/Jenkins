package com.ykyy.server.web;

import com.ykyy.server.bean.JsonResult;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.service.UserService;
import com.ykyy.server.util.JsonReslutUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            int result = userService.addUser(userBean);
            JsonReslutUtil.getJsonResult(result,r);

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
            JsonReslutUtil.getJsonResult(result,r);

        }
        catch (Exception e)
        {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping("/updataUser")
    public ResponseEntity<JsonResult> upDataUser(@RequestBody UserBean userBean)
    {
        JsonResult r = new JsonResult();
        try
        {
            int result = userService.updateUser(userBean);
            JsonReslutUtil.getJsonResult(result,r);

        }
        catch (Exception e)
        {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value ="/getall", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getAll()
    {
        JsonResult r = new JsonResult();
        try
        {
            List<UserBean> list = userService.getALl();
            r.setResult(list);
            r.setStatus("success");
        }
        catch (Exception e)
        {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value="/getuserpage/{begin}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserPage(@PathVariable int begin)
    {
        JsonResult r = new JsonResult();
        try
        {
            List<UserBean> list = userService.getUserPage(begin*10);
            r.setResult(list);
            r.setStatus("success");
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
