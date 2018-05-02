package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ResultBean;
import com.ykyy.server.exception.Exceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(value = "/category", description = "分类")
public class CategoryController extends BaseController
{
    @PostMapping("/addcategory")
    @ApiOperation(value = "添加分类", notes = "添加分类")
    public String addCategory(@RequestBody @ApiParam(name = "分类",value = "{\"category_name\": \"123\"}") CategoryBean categoryBean)
    {
        Integer result = categoryService.addGateory(categoryBean);
        if(result ==null || result == 0)
        {
            throw Exceptions.get403Exception("添加分类失败");
        }

        return JSONObject.toJSON(new ResultBean(200, "添加分类成功")).toString();
    }

    @GetMapping("/deletecategory/{id}")
    @ApiOperation(value = "删除分类", notes = "删除分类")
    public String deleteCategory(@PathVariable(value = "id") Integer id)
    {
        Integer result = categoryService.deleteGeteory(id);
        if(result ==null || result == 0)
        {
            throw Exceptions.get403Exception("删除分类失败");
        }

        return JSONObject.toJSON(new ResultBean(200, "删除分类成功")).toString();
    }

    @GetMapping("/getcategory")
    @ApiOperation(value = "获取所有分类", notes = "获取所有分类")
    public String getCategory()
    {
        List<CategoryBean> list = categoryService.getCategory();
        if(list==null)
        {
            throw Exceptions.get403Exception("查询分类失败");
        }
        return JSONObject.toJSON(list).toString();
    }

    @GetMapping("/getcategorybyid/{id}")
    @ApiOperation(value = "获取id分类", notes = "获取id分类")
    public String getCategoryById(@PathVariable("id") Integer id)
    {
        CategoryBean categoryBean = categoryService.getCategoryById(id);
        if(categoryBean==null)
        {
            throw Exceptions.get403Exception("查询分类失败");
        }
        return JSONObject.toJSON(categoryBean).toString();
    }

    @PostMapping("/updatecategory")
    @ApiOperation(value = "更新分类", notes = "更新分类")
    public String updateCategory(@RequestBody @ApiParam(name = "category",value = "{\"category_id\": 7,\"category_name\": \"体育\"}") CategoryBean categoryBean)
    {
        if(categoryBean.getCategory_id()==null || categoryBean.getCategory_name()==null)
        {
            throw Exceptions.get404Exception("更新数据失败");
        }

        CategoryBean bean = categoryService.updateCategory(categoryBean.getCategory_id(), categoryBean.getCategory_name());
        if(bean==null)
        {
            throw Exceptions.get404Exception("更新数据失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "更新分类成功")).toString();
    }
}
