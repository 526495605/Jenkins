package com.ykyy.server.web;

import com.alibaba.fastjson.JSONObject;
import com.ykyy.server.bean.PurchaseBean;
import com.ykyy.server.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController
{
    @Autowired
    PurchaseService purchaseService;

    Integer addPurchase(PurchaseBean purchaseBean)
    {
        return null;
    }

    Integer addPurchaseSuss(Integer purchase_id)
    {
        return null;
    }

    @GetMapping("/getAllPurchase/{users_id}/{purchase_status}")
    public String getAllPurchase(@PathVariable("users_id") Integer users_id, @PathVariable("purchase_status") Integer purchase_status)
    {
        List<PurchaseBean> list = purchaseService.getAllPurchase(users_id, purchase_status);
        return JSONObject.toJSON(list).toString();
    }
}
