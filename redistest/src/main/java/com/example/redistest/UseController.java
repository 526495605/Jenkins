package com.example.redistest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class UseController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    /**
     * 测试插入
     * @return
     */
    @RequestMapping("/add/{id}/{name}/{wx}")
    public String add(@PathVariable("id") int id,@PathVariable("name") String userName, @PathVariable("wx") String wx){
        User u = new User();
        u.setUsers_id(id);
        u.setUsers_name(userName);
        u.setUsers_wx(wx);
        this.userService.insert(u);
        return u.getUsers_id()+"    " + u.getUsers_name();
    }

    /**
     * 测试根据id查询
     * @return
     */
    @RequestMapping("/get/{id}")
    @ResponseBody
    public String findById(@PathVariable("id")int id){
        User u = this.userService.find(id);
        return u== null ? "找不到对象" :( u.getUsers_id()+"    " + u.getUsers_name());
    }


    /**
     * 测试修改
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(int id,String userName){
        User u = new User();
        u.setUsers_id(id);
        u.setUsers_wx(userName);
        this.userService.update(u);
        return u.getUsers_id()+"    " + u.getUsers_name();
    }

    /**
     * 测试删除
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id")int id){
        this.userService.delete(id);
        return "success";
    }
}
