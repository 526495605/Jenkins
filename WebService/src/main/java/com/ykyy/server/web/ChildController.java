package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.ResultBean;
import com.ykyy.server.exception.Exceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/child")
@Api(value = "/child", description = "儿童")
public class ChildController extends BaseController
{

    /**
     * @Author:owen
     * @Description:添加儿童
     * @Date:Create in 20:38 2018/4/23
     * @Modified By:
     */
    @PostMapping("/addchild")
    @ApiOperation(value="添加儿童", notes="添加儿童")
    public String add(@RequestBody  @ApiParam(name = "儿童",value = "{\"child_age\": \"30\",\"child_father_idcard\": \"140108199111271611\",\"child_father_name\": \"owen\", \"child_father_tel\": \"18636920124\", \"child_grade\": \"9854\", \"child_health\": \"abc\", \"child_healthinfo\": \"adb\", \"child_height\": \"176\", \"child_idcard\": \"shenfenz\", \"child_idcardnum\": \"140108199111271611\", \"child_mother_idcard\": \"140108199111271611\", \"child_mother_name\": \"owen\", \"child_mother_tel\": \"18636920124\", \"child_name\": \"string\", \"child_nation\": \"string\", \"child_sex\": \"男\",\"child_tel\": \"18636920124\",  \"users_id\": 1,\"child_school\": \"123456\"}") ChildBean childBean)
    {
        Integer result = childService.addChild(childBean);
        if(result==null || result ==0)
        {
          throw Exceptions.get403Exception("添加失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "添加儿童成功")).toString();
    }

    @GetMapping("/getchildbyid/{users_id}/{child_id}")
    @ApiOperation(value="通过id查询儿童", notes="通过id查询儿童")
    public String getChildById(@PathVariable(value = "users_id")  int users_id,
                               @PathVariable(value = "child_id")  int child_id)
    {
        System.out.println(users_id+"  ----  "+child_id);
        ChildBean childBean = childService.getChildById(users_id, child_id);
       // ChildBean childBean=null;
        if(childBean==null)
        {
          throw Exceptions.get404Exception("id有误，查询失败");
        }
        return JSONObject.toJSON(childBean).toString();
    }

  @GetMapping("/deletChild/{users_id}/{child_id}")
  @ApiOperation(value = "删除儿童", notes = "删除儿童")
   public String deletChild(@PathVariable(value = "users_id") Integer users_id,
                            @PathVariable(value = "child_id") Integer child_id)
   {
     Integer result = childService.deletChild(users_id, child_id);
     if(result==null || result==0)
     {
       throw Exceptions.get404Exception("id有误，删除失败");
     }
     return JSONObject.toJSON(new ResultBean(200, "删除成功！")).toString();
   }

    @PostMapping("/updateChild")
    @ApiOperation(value="修改儿童", notes="修改儿童")
    public String updateChild(@RequestBody ChildBean childBean)
    {
      ChildBean bean = childService.updateChild(childBean);
      return JSONObject.toJSON(bean).toString();
    }
}

/*
*    @UpdateProvider(type = ChildProvider.class,method = "updateChild")
    Integer updateChild(ChildBean childBean);

    @Insert("INSERT child{users_id, child_name, child_age, child_sex, child_height, child_nation, child_tel, child_grade, child_idcard, child_idcardnum, child_health, child_healthinfo, child_father_name, child_father_tel, child_father_idcard, child_mother_name, child_mother_tel, child_mother_idcard} VALUE(#{users_id} #{child_name}, #{child_age}, #{child_sex}, #{child_height}, #{child_nation}, #{child_tel}, #{child_grade}, #{child_idcard}, #{child_idcardnum}, #{child_health}, #{child_healthinfo}, #{child_father_name}, #{child_father_tel}, #{child_father_idcard}, #{child_mother_name}, #{child_mother_tel}, #{child_mother_idcard})")
    Integer addChild(ChildBean childBean);

    @Delete("UPDATE child SET child_status=0 WHERE users_id=#{users_id} and child_id=#{child_id}")
    Integer deletChild(Integer users_id, Integer child_id);

    @Select("SELECT child_name, child_age, child_sex, child_height, child_nation, child_tel, child_grade, child_idcard, child_idcardnum, child_health, child_healthinfo, child_father_name, child_father_tel, child_father_idcard, child_mother_name, child_mother_tel, child_mother_idcard FROM child WHERE users_id=#{0} and child_id=#{1} and child_status=1")
    ChildBean getChildById(Integer users_id, Integer child_id);
    */
