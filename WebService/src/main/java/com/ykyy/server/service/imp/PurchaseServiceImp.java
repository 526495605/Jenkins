package com.ykyy.server.service.imp;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.ykyy.server.bean.PurchaseBean;
import com.ykyy.server.dao.PurchaseMapping;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public Integer addPurchase(Integer[] child_ids, Integer product_id,Integer users_id)
    {
        try
        {
            for(int i = 0; i<child_ids.length; i++)
            {
                purchaseMapping.addPurchase(child_ids[i] , product_id, users_id);
            }
        }
        catch (DataAccessException e)
        {
            Throwable throwable = e.getCause();
            if(throwable instanceof MySQLIntegrityConstraintViolationException)
            {
                throw Exceptions.get409Exception("插入数据有误");
            }else
            {
                e.printStackTrace();
            }
        }
        return 1;
    }

    @Override
    public Integer addPurchaseSuss(Integer purchase_id)
    {
        return purchaseMapping.addPurchaseSuss(purchase_id);
    }

    @Override
    public List<PurchaseBean> getAllPurchase(Integer users_id, Integer purchase_status)
    {
        return purchaseMapping.getAllPurchase(users_id, purchase_status);
    }

    @Override
    public Integer addPurchaseComplet(Integer purchase_id)
    {
        return purchaseMapping.addPurchaseComplet(purchase_id);
    }

    @Override
    public Integer addUserComment(Integer purchase_id, String comment)
    {
        return purchaseMapping.addUserComment(purchase_id,comment);
    }

    @Override
    public Integer addChildComment(Integer purchase_id, String comment)
    {
        return purchaseMapping.addChildComment(purchase_id, comment);
    }

    @Override
    public List<PurchaseBean> getPurchaseById(Integer purchase_id)
    {
        return purchaseMapping.getPurchaseById(purchase_id);
    }

}
