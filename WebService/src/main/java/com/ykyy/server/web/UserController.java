package com.ykyy.server.web;

import com.ykyy.server.bean.JsonResult;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.service.UserService;
import com.ykyy.server.util.JsonReslutUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/user")
@Api(value = "/user", description = "用户")
@CacheConfig(cacheNames="userCache")
@Transactional(propagation = Propagation.REQUIRED,readOnly=false,rollbackFor = Exception.class)
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value="/add",method = RequestMethod.PUT)
    @ApiOperation(value="添加用户", notes="添加用户")
    public ResponseEntity<JsonResult> addUser(@RequestBody @ApiParam(name = "body") UserBean userBean)
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


    @Cacheable(key = "#p0")
    @ApiOperation(value="通过ID查询用户", notes="通过ID查询用户")
    @RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
    public UserBean getUserById(@PathVariable(value = "id") int id)
    {
        JsonResult r = new JsonResult();
        UserBean user =null;
        try
        {
            user = userService.getUser(id);
            //System.out.println(id);
            r.setResult(user);
            r.setStatus("ok");
        } catch (Exception e)
        {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return user;
    }

    @ApiOperation(value="删除用户", notes="删除用户")
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

    @ApiOperation(value="更新用户", notes="更新用户")
    @RequestMapping(value="/updataUser", method = RequestMethod.POST)
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

    @ApiOperation(value="查询所有用户", notes="查询所有用户")
    @RequestMapping(value ="/getall", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getAll() throws Exception
    {
        //throw new Exception();
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

    @ApiOperation(value="分页查找用户", notes="分页查找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "begin", value = "页数", required = true, dataType = "Integer", paramType = "path"),
    })
    @RequestMapping(value="/getuserpage/{begin}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getUserPage(@PathVariable(value = "begin") int begin)
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

    @ApiOperation(value="登录", notes="登录")
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> login(@RequestBody UserBean userBean)
    {
        JsonResult r = new JsonResult();
        try
        {
            int num = userService.login(userBean);
            r.setResult(num);
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
