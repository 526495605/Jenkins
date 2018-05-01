package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.ProductBean;
import com.ykyy.server.bean.ResultBean;
import com.ykyy.server.exception.Exceptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value = "/product", description = "产品")
public class ProductController extends BaseController
{

    @PostMapping("/addproduct")
    @ApiOperation(value = "添加产品", notes = "添加产品")
    public String addProduct(@RequestBody @ApiParam(value = "{\"product_from\": \"string\",\"product_money\": 399,\"product_name\": \"string\",\"product_theme\": \"string\",\"product_to\": \"string\", \"product_url\": \"string\"}") ProductBean productBean)
    {
        Integer result = productService.addProduct(productBean);
        if (result == null || result == 0)
        {
            throw Exceptions.get403Exception("添加产品失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "添加成功")).toString();
    }

    @GetMapping("/getproduct")
    @ApiOperation(value = "获取所有产品", notes = "获取所有产品")
    public String getProduct()
    {
        List<ProductBean> list = productService.getProduct();
        if (list == null || list.size() == 0)
        {
            throw Exceptions.get403Exception("查询失败");
        }
        return JSONObject.toJSON(list).toString();
    }

    @GetMapping("/getproductbyid/{id}")
    @ApiOperation(value = "通过id查找产品", notes = "通过id查找产品")
    public String getProductById(@PathVariable(value = "id") Integer id)
    {
        ProductBean productBean = productService.getProductById(id);
        if (productBean == null)
        {
            throw Exceptions.get403Exception("查询失败");
        }
        return JSONObject.toJSON(productBean).toString();
    }

    @GetMapping("/deletetproductbyid/{id}")
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public String deleteProductById(@PathVariable(value = "id") Integer id)
    {
        Integer result = productService.deleteProductById(id);
        if (result == null || result == 0)
        {
            throw Exceptions.get403Exception("删除失败");
        }
        return JSONObject.toJSON(new ResultBean(200, "删除成功")).toString();
    }

    @PostMapping("/updateproduct")
    @ApiOperation(value = "更新产品", notes = "更新产品")
    public String updateProduct(@RequestBody ProductBean productBean)
    {
        ProductBean bean = productService.UpdateProduct(productBean);
        if(bean==null)
            throw Exceptions.get403Exception("更新失败");

        return JSONObject.toJSON(new ResultBean(200, "修改成功")).toString();
    }
}
