package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.Token;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.util.MD5Util;
import com.ykyy.server.util.Sms;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value="/user")
@Api(value = "/user", description = "用户")
public class UserController extends BaseController
{

    @PostMapping(value = "/login")
    public String login(
            @RequestBody @ApiParam(name = "body",defaultValue = "{\"phone\":\"189797979\",\"password\":\"123\"}", value = "{\"phone\":\"189797979\",\"password\":\"123\"}") String body)
    {
        JSONObject jsonObject = JSONObject.parseObject(body);
        String phone = jsonObject.getString("phone");
        String password = jsonObject.getString("password");

        if("".equals(phone) || "".equals(password))
        {
            throw Exceptions.get404Exception("用户名不能为空");
        }
        System.out.println(phone + " + "+ password);
        UserBean user = userService.login(phone, password);
        if(user == null)
        {
            throw Exceptions.get404Exception("用户名或者密码错误");
        }
        Token token = null;
        token = saveToken(user.getUsers_id());
        token.setUsers_id(user.getUsers_id());
        token.setUsers_phone(user.getUsers_phone());
        return JSONObject.toJSON(token).toString();

    }



  @RequestMapping(value="/add",method = RequestMethod.POST)
    @ApiOperation(value="添加用户", notes="添加用户")
    public String addUser(@RequestBody @ApiParam(name = "body",value = "{\"users_password\": \"123\",\"users_phone\": \"18600000000\",\"users_parent\": \"1\"}") UserBean userBean)
    {
        if(!Sms.isMobile(userBean.getUsers_phone()))
        {
            throw Exceptions.get400Exception("号码输入错误");
        }
        userBean.setUsers_password(MD5Util.MD5(userBean.getUsers_password()));

        Integer result = userService.addUser(userBean);

        if(userBean==null)
        {
            throw Exceptions.get409Exception("号码已经注册");
        }
        return "{\"status\":true,\"msg\":\"号码可以使用\"}";

    }


    @ApiOperation(value="通过ID查询用户", notes="通过ID查询用户")
    @RequestMapping(value = "/getuserbyid/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable(value = "id") int id)
    {
        UserBean user =null;
        user = userService.getUserById(id);
        if(user==null)
            throw Exceptions.get404Exception("查询失败");

        return JSONObject.toJSON(user).toString();
    }

    @ApiOperation(value="通过Phone查询用户", notes="通过phone查询用户")
    @RequestMapping(value = "/getuserbyphone/{phone}", method = RequestMethod.GET)
    public String getUserByPhone(@PathVariable(value = "phone") String phone)
    {
        UserBean user =null;
        user = userService.getUserByPhone(phone);
        if(user==null)
            throw Exceptions.get404Exception("查询失败");

        return JSONObject.toJSON(user).toString();
    }

    @ApiOperation(value="删除用户", notes="删除用户")
    @RequestMapping(value="/deleteuser/{id}/{phone}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(value = "id") int id, @PathVariable(value = "phone") String phone)
    {
        String timemd5 = String.valueOf(new Date().getTime());
        Integer result = userService.deleteUser(id, phone+"+"+timemd5);
        if(result == null || result == 0)
        {
            throw Exceptions.get400Exception("删除失败");
        }

        return "{\"code\": \"200\",\"message\": \"删除成功\"}";
    }

    @ApiOperation(value="更新用户", notes="更新用户")
    @RequestMapping(value="/updataUser", method = RequestMethod.POST)
    public String upDataUser(@RequestBody UserBean userBean)
    {
        UserBean user = userService.updateUser(userBean);
        if(user==null)
        {
            throw Exceptions.get400Exception("更新失败");
        }

        return JSONObject.toJSON(user).toString();
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = "/changepass")
    public String changePass(@RequestBody @ApiParam(value = "{\"user_id\": \"1\",\"oldpass\": \"123\",\"newpass\": \"123\"}") String body)
    {
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer user_id = jsonObject.getInteger("user_id");
        String oldpass = jsonObject.getString("oldpass");
        String newpass = jsonObject.getString("newpass");

        Integer result = userService.changePass(user_id, oldpass, newpass);
        if(result==null || result==0)
            throw Exceptions.get409Exception("修改密码失败");

        return "{\"code\": \"200\",\"message\": \"修改密码成功\"}";
    }


    /*

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
    */

}
