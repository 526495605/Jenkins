package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.Token;
import com.ykyy.server.exception.Exceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Integer id = userService.login(phone, password);
        if(id == null)
        {
            throw Exceptions.get404Exception("用户名或者密码错误");
        }
        Token token = null;
        token = saveToken(id);
        return JSONObject.toJSON(token).toString();

    }



   /* @RequestMapping(value="/add",method = RequestMethod.PUT)
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
    */

}
