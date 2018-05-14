package com.ykyy.server.service;

import com.ykyy.server.bean.PurchaseBean;
import com.ykyy.server.provider.PurchaseProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PurchaseService
{
    Integer addPurchase(Integer[] child_ids, Integer product_id,Integer users_id);

    Integer addPurchaseSuss(Integer purchase_id);

    List<PurchaseBean> getAllPurchase(Integer users_id, Integer purchase_status);

    Integer addPurchaseComplet(Integer purchase_id);

    Integer addUserComment(Integer purchase_id, String comment);

    Integer addChildComment(Integer purchase_id, String comment);

    List<PurchaseBean> getPurchaseById(Integer purchase_id);
}
