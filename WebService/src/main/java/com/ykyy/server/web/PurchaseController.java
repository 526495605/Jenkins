package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.ykyy.server.bean.PurchaseBean;
import com.ykyy.server.bean.ResultBean;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@Api(value = "/purchase", description = "订单详情")
public class PurchaseController extends BaseController
{

    @PostMapping("/addPurchase/{users_id}/{product_id}")
    @ApiOperation(value = "添加订单（未支付）", notes = "添加订单（未支付）")
    public String addPurchase(@PathVariable("users_id") Integer users_id, @PathVariable("product_id") Integer product_id,@RequestBody @ApiParam(name = "儿童id", value = "[ \"3\", \"4\", \"5\" ]") Integer[] child_ids)
    {
        purchaseService.addPurchase(child_ids,  product_id,users_id);
        return JSONObject.toJSON(new ResultBean(200, "添加成功")).toString();
    }

    @GetMapping("/addPurchaseSuss/{purchase_id}")
    @ApiOperation(value = "支付完成", notes = "支付完成")
    public String addPurchaseSuss(@PathVariable(value = "purchase_id") Integer purchase_id)
    {
        int num = purchaseService.addPurchaseSuss(purchase_id);
        if (num == 0)
        {
            throw Exceptions.get404Exception("purchaseid输入有误");
        }
        return JSONObject.toJSON(new ResultBean(200, "购买成功")).toString();
    }

    @GetMapping("/getAllPurchase/{users_id}/{purchase_status}")
    @ApiOperation(value = "查询user的所有订单", notes = "查询user的所有订单，0为失效，1为下单未支付，2为支付成功，3为游学完成")
    public String getAllPurchase(@PathVariable("users_id") Integer users_id, @PathVariable("purchase_status") Integer purchase_status)
    {
        List<PurchaseBean> list = purchaseService.getAllPurchase(users_id, purchase_status);
        return JSONObject.toJSON(list).toString();
    }

    @GetMapping("/addPurchaseComplet/{purchase_id}")
    @ApiOperation(value = "订单完成", notes = "支付成功之后，才可以订单完成")
    public String addPurchaseComplet(@PathVariable("purchase_id") Integer purchase_id)
    {
        int num = purchaseService.addPurchaseComplet(purchase_id);
        if (num == 0)
        {
            throw Exceptions.get404Exception("purchaseid输入有误");
        }
        return JSONObject.toJSON(new ResultBean(200, "订单完成")).toString();
    }

    @PostMapping("/addUserComment/{purchase_id}")
    @ApiOperation(value = "添加用户评论", notes = "订单完成之后，才可以添加用户评论")
    public String addUserComment(@PathVariable("purchase_id") Integer purchase_id, @RequestBody @ApiParam(value = "\"很nice\"") String comment)
    {
        int num = purchaseService.addUserComment(purchase_id, comment);
        if(num == 0)
        {
            throw Exceptions.get404Exception("id有误");
        }
        return JSONObject.toJSON(new ResultBean(200, "用户添加评论成功")).toString();
    }

    @PostMapping("/addChildComment/{purchase_id}")
    @ApiOperation(value = "添加儿童评论", notes = "订单完成之后，才可以添加ertong评论")
    public String addChildComment(@PathVariable("purchase_id") Integer purchase_id, @RequestBody @ApiParam(value = "\"很nice\"") String comment)
    {
        int num = purchaseService.addChildComment(purchase_id, comment);
        if(num == 0)
        {
            throw Exceptions.get404Exception("id有误");
        }
        return JSONObject.toJSON(new ResultBean(200, "儿童添加评论成功")).toString();
    }

    @GetMapping("/getPurchaseById/{purchase_id}")
    @ApiOperation(value = "通过purcahse_id查询", notes = "status：1为下单未支付，2为支付成功，3为游学完成")
    public String getPurchaseById(@PathVariable("purchase_id") Integer purchase_id)
    {
        List<PurchaseBean> list = purchaseService.getPurchaseById(purchase_id);
        if(list==null)
        {
            throw Exceptions.get404Exception("purchaseid有误");
        }
        return JSONObject.toJSON(list).toString();
    }
}
