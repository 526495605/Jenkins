package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.*;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.util.MD5Util;
import com.ykyy.server.util.Sms;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/user")
@Api(value = "/user", description = "用户")
public class UserController extends BaseController
{

    @PostMapping(value = "/login")
    @ApiOperation(value="登录", notes="登录")
    public String login(
            @RequestBody @ApiParam(name = "body",defaultValue = "{\"users_phone\":\"189797979\",\"users_password\":\"123\"}", value = "{\"users_phone\":\"189797979\",\"users_password\":\"123\"}") String body)
    {
        JSONObject jsonObject = JSONObject.parseObject(body);
        String phone = jsonObject.getString("users_phone");
        String password = jsonObject.getString("users_password");

        if("".equals(phone) || "".equals(password))
        {
            throw Exceptions.get400Exception("用户名不能为空");
        }
        if(!Sms.isMobile(phone))
        {
            throw Exceptions.get403Exception("号码输入错误");
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
            throw Exceptions.get403Exception("号码输入错误");
        }


        userBean.setUsers_password(MD5Util.MD5(userBean.getUsers_password()));

        Integer result = userService.addUser(userBean);

        //"{\"status\":true,\"msg\":\"号码可以使用\"}"
        return JSONObject.toJSON(new ResultBean(200, "号码可以使用")).toString();

    }


    @ApiOperation(value="通过ID查询用户", notes="通过ID查询用户")
    @RequestMapping(value = "/getuserbyid/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable(value = "id") int id)
    {
        UserBean user =null;
        user = userService.getUserById(id);
        if(user==null)
        {
            throw Exceptions.get404Exception("用户id不存在");
        }

        return JSONObject.toJSON(user).toString();
    }

    @ApiOperation(value="通过Phone查询用户", notes="通过phone查询用户")
    @RequestMapping(value = "/getuserbyphone/{phone}", method = RequestMethod.GET)
    public String getUserByPhone(@PathVariable(value = "phone") String phone)
    {
        UserBean user =null;
        user = userService.getUserByPhone(phone);
        if(!Sms.isMobile(phone))
        {
            throw Exceptions.get403Exception("号码输入错误");
        }
        if(user==null)
        {
            throw Exceptions.get404Exception("手机号错误，查询失败");
        }

        return JSONObject.toJSON(user).toString();
    }

    @ApiOperation(value="删除用户", notes="删除用户")
    @RequestMapping(value="/deleteuser/{id}/{phone}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(value = "id") int id, @PathVariable(value = "phone") String phone)
    {
        if(!Sms.isMobile(phone))
        {
            throw Exceptions.get403Exception("号码输入错误");
        }
        String timemd5 = String.valueOf(System.currentTimeMillis());
        Integer result = userService.deleteUser(id, phone+"+"+timemd5);
        if(result == null || result == 0)
        {
            throw Exceptions.get400Exception("删除失败");
        }

        return JSONObject.toJSON(new ResultBean(200, "删除成功")).toString();
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

        Integer result = userService.changePass(user_id, oldpass, MD5Util.MD5(newpass));
        if(result==null || result==0)
        {
            throw Exceptions.get409Exception("修改密码失败");
        }

        return JSONObject.toJSON(new ResultBean(200, "修改密码成功")).toString();
    }

    @ApiOperation(value = "查询用户所有孩子", notes = "查询用户所有孩子")
    @GetMapping(value = "/getchildbyuserid/{id}")
    public String getChildById(@PathVariable(value = "id") int users_id)
    {
        List<ChildBean> list = userService.getChildById(users_id);

        return JSONObject.toJSON(list).toString();
    }

//    /------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getuserscategory/{id}")
    @ApiOperation(value = "获取用户标签", notes = "获取用户标签")
    public String getUsersCategory(@PathVariable(value = "id") Integer users_id)
    {
        List<CategoryBean> result = userService.getUsersCategory(users_id);
        if(result==null)
        {
            throw Exceptions.get404Exception("用户id不存在");
        }
        String json =  JSONObject.toJSON(result).toString();
        return json;
    }

    @PostMapping("/insertuserscategory/{users_id}")
    @ApiOperation(value = "添加用户标签", notes = "添加用户标签")
    public String insertUsersCategory(@PathVariable(value = "users_id") Integer users_id, @RequestBody @ApiParam(name = "数组", value = "[ \"7\", \"8\", \"9\" ]") String body)
    {
        Integer[] category_id = JSONObject.parseObject(body, Integer[].class);
        if(category_id==null)
        {
            throw Exceptions.get403Exception("输入错误");
        }
        Integer result = userService.insertUsersCategory(users_id, category_id);
        return JSONObject.toJSON(new ResultBean(200, "添加用户标签成功")).toString();
    }

    @GetMapping("/deleteuserscategoryaall/{users_id}")
    @ApiOperation(value = "删除用户所有标签", notes = "删除用户所有标签")
    public String deleteUsersCategoryaAll(@PathVariable(value = "users_id") Integer users_id)
    {
        Integer result = userService.deleteUsersCategoryaAll(users_id);
        if(result==null)
        {
            throw Exceptions.get404Exception("删除标签失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "删除用户标签成功")).toString();
    }

    @GetMapping("/deleteuserscategoryById/{id}")
    @ApiOperation(value = "通过id删除用户标签", notes = "通过id删除用户标签")
    public String deleteUsersCategoryById(@PathVariable(value = "id") Integer id)
    {
        Integer result = userService.deleteUsersCategoryById(id);
        if(result==null)
        {
            throw Exceptions.get404Exception("删除标签失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "删除用户标签成功")).toString();
    }

    @PostMapping("/changeCategoryById/{id}")
    @ApiOperation(value = "通过id修改户标签", notes = "通过id修改户标签")
    public String changeCategoryById(@PathVariable(value = "id")Integer users_id, @RequestBody @ApiParam("数组") Integer[] category_ids)
    {
        Integer result = userService.changeCategoryById(users_id, category_ids);
        if(result == null)
        {
            throw Exceptions.get403Exception("usersid，错误");
        }
        return JSONObject.toJSON(new ResultBean(200, "修改用户标签成功")).toString();

    }
}
