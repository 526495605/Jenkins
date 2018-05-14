package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.ResultBean;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.util.IDCardUtil;
import com.ykyy.server.util.Sms;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if(childBean.getUsers_id()==null)
        {
            throw Exceptions.get404Exception("请输入userid");
        }
        if(childBean.getChild_tel()==null || !Sms.isMobile(childBean.getChild_tel()))
        {
            throw Exceptions.get400Exception("儿童手机输入错误");
        }
        if(childBean.getChild_mother_tel()==null ||! Sms.isMobile(childBean.getChild_mother_tel()))
        {
            throw Exceptions.get400Exception("监护二人手机输入错误");
        }
        if(childBean.getChild_father_tel()==null || !Sms.isMobile(childBean.getChild_father_tel()))
        {
            throw Exceptions.get400Exception("监护人一手机输入错误");
        }
        if(childBean.getChild_father_idcard() == null || !IDCardUtil.isIDCard(childBean.getChild_father_idcard()))
        {
            throw Exceptions.get400Exception("监护人一IDcard输入错误");
        }
        if(childBean.getChild_mother_idcard() == null || !IDCardUtil.isIDCard(childBean.getChild_mother_idcard()))
        {
            throw Exceptions.get400Exception("监护人二IDcard输入错误");
        }
        if(childBean.getChild_idcardnum() == null || !IDCardUtil.isIDCard(childBean.getChild_idcardnum()))
        {
            throw Exceptions.get400Exception("监护人一IDcard输入错误");
        }
        return childService.addChild(childBean);
    }

    @GetMapping("/getchildbyid/{users_id}/{child_id}")
    @ApiOperation(value="通过id查询儿童", notes="通过id查询儿童")
    public String getChildById(@PathVariable(value = "users_id")  int users_id,
                               @PathVariable(value = "child_id")  int child_id)
    {
        ChildBean childBean = childService.getChildById(users_id, child_id);
        return JSONObject.toJSON(childBean).toString();
    }

  @GetMapping("/deletChild/{users_id}/{child_id}")
  @ApiOperation(value = "删除儿童", notes = "删除儿童")
   public String deletChild(@PathVariable(value = "users_id") Integer users_id,
                            @PathVariable(value = "child_id") Integer child_id)
   {
     Integer result = childService.deletChild(users_id, child_id);
     if(result==null || result== 0 )
     {
       throw Exceptions.get404Exception("id有误，删除失败");
     }
     return JSONObject.toJSON(new ResultBean(200, "删除成功！")).toString();
   }

    @PostMapping("/updateChild")
    @ApiOperation(value="修改儿童", notes="修改儿童")
    public String updateChild(@RequestBody ChildBean childBean)
    {
        if(childBean.getUsers_id()==null)
        {
            throw Exceptions.get404Exception("请输入userid");
        }
        if(childBean.getChild_tel()==null || Sms.isMobile(childBean.getChild_tel()))
        {
            throw Exceptions.get400Exception("儿童手机输入错误");
        }
        if(childBean.getChild_mother_tel()==null || Sms.isMobile(childBean.getChild_mother_tel()))
        {
            throw Exceptions.get400Exception("监护二人手机输入错误");
        }
        if(childBean.getChild_father_tel()==null || Sms.isMobile(childBean.getChild_father_tel()))
        {
            throw Exceptions.get400Exception("监护人一手机输入错误");
        }
        if(childBean.getChild_father_idcard() == null || IDCardUtil.isIDCard(childBean.getChild_father_idcard()))
        {
            throw Exceptions.get400Exception("监护人一IDcard输入错误");
        }
        if(childBean.getChild_mother_idcard() == null || IDCardUtil.isIDCard(childBean.getChild_mother_idcard()))
        {
            throw Exceptions.get400Exception("监护人二IDcard输入错误");
        }
        if(childBean.getChild_idcardnum() == null || IDCardUtil.isIDCard(childBean.getChild_idcardnum()))
        {
            throw Exceptions.get400Exception("监护人一IDcard输入错误");
        }
      ChildBean bean = childService.updateChild(childBean);
      return JSONObject.toJSON(bean).toString();
    }

    @GetMapping("/getchildcategory/{id}")
    @ApiOperation(value = "获取儿童标签", notes = "获取儿童标签")
    public String getChildCategory(@PathVariable(value = "id") Integer child_id)
    {
        List<CategoryBean> result = childService.getChildCategory(child_id);
        if(result==null)
        {
            throw Exceptions.get404Exception("查询标签失败");
        }
        return JSONObject.toJSON(result).toString();
    }


    //标签

    @PostMapping("/insertchildcategory/{child_id}")
    @ApiOperation(value = "添加儿童标签", notes = "添加儿童标签")
    public String insertChildCategory(@PathVariable(value = "child_id") Integer child_id, @RequestBody @ApiParam(name = "数组", value = "[ \"7\", \"8\", \"9\" ]") String body)
    {
        Integer[] category_id = JSONObject.parseObject(body, Integer[].class);
        if(category_id==null)
        {
            throw Exceptions.get403Exception("输入JSON错误");
        }
        Integer result = childService.insertChildCategory(child_id, category_id);
        if(result==null)
        {
            throw Exceptions.get404Exception("添加标签失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "添加儿童标签成功")).toString();
    }

    @GetMapping("/deletechildcategoryaall/{child_id}")
    @ApiOperation(value = "删除儿童所有标签", notes = "删除儿童所有标签")
    public String deleteChildCategoryaAll(@PathVariable(value = "child_id") Integer child_id)
    {
        Integer result = childService.deleteChildCategoryaAll(child_id);
        if(result==null)
        {
            throw Exceptions.get404Exception("删除标签失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "删除儿童标签成功")).toString();
    }

    @GetMapping("/deletechildcategoryById/{id}")
    @ApiOperation(value = "通过id删除儿童标签", notes = "通过id删除儿童标签")
    public String deleteChildCategoryById(@PathVariable(value = "id") Integer id)
    {
        Integer result = childService.deleteChildCategoryById(id);
        if(result==null || result ==0)
        {
            throw Exceptions.get404Exception("删除标签失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "删除儿童标签成功")).toString();
    }

    @PostMapping("/changChildCategoryById/{child_id}")
    @ApiOperation(value = "通过id修改儿童标签", notes = "通过id修改儿童标签")
    public String changChildCategoryById(@PathVariable(value = "child_id") Integer child_id, @RequestBody @ApiParam(name = "数组", value = "[ \"7\", \"8\", \"9\" ]")  Integer[] category_ids)
    {
        Integer result = childService.changeChildCategoryById(child_id, category_ids);
        if(result == null)
        {
            throw Exceptions.get404Exception("修改失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "修改儿童标签成功")).toString();
    }
}