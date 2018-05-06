package com.ykyy.server.service;

import com.ykyy.server.bean.PurchaseBean;
import com.ykyy.server.provider.PurchaseProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PurchaseService
{
    Integer addPurchase(PurchaseBean purchaseBean);

    Integer addPurchaseSuss(Integer purchase_id);

    List<PurchaseBean> getAllPurchase(Integer users_id, Integer purchase_status);
}
