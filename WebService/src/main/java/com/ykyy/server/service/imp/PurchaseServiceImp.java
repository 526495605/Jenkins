package com.ykyy.server.service.imp;

import com.ykyy.server.bean.PurchaseBean;
import com.ykyy.server.dao.PurchaseMapping;
import com.ykyy.server.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
public class PurchaseServiceImp implements PurchaseService
{

    @Autowired
    PurchaseMapping purchaseMapping;

    @Override
    public Integer addPurchase(PurchaseBean purchaseBean)
    {
        return null;
    }

    @Override
    public Integer addPurchaseSuss(Integer purchase_id)
    {
        return null;
    }

    @Override
    public List<PurchaseBean> getAllPurchase(Integer users_id, Integer purchase_status)
    {
        return purchaseMapping.getAllPurchase(users_id, purchase_status);
    }
}
