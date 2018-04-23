package com.ykyy.server.service.serviceimp;

import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.ProductBean;
import com.ykyy.server.dao.ChildMapping;
import com.ykyy.server.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;

public class ChildServiceImp implements ChildService
{
    @Autowired
    ChildMapping childMapping;

    @Override
    public int add(ChildBean childBean)
    {
        return childMapping.add(childBean);
    }

    @Override
    public int getChildCountByUserId(int users_id)
    {
        return childMapping.getChildCountByUserId(users_id);
    }

    @Override
    public ChildBean[] getChildByUserId(int users_id)
    {
        return childMapping.getChildByUserId(users_id);
    }

    @Override
    public int getChildCountByProductId(int product_id)
    {
        return childMapping.getChildCountByProductId(product_id);
    }

    @Override
    public ChildBean[] getChildByProductId(int product_id)
    {
        return childMapping.getChildByProductId(product_id);
    }

    @Override
    public ProductBean[] getProductByChildId(int child_id)
    {
        return childMapping.getProductByChildId(child_id);
    }

    @Override
    public int UpdateChild(ChildBean childBean)
    {
        return childMapping.UpdateChild(childBean);
    }

    @Override
    public int deleteChild(int users_id, int child_id)
    {
        return childMapping.deleteChild(users_id,child_id);
    }
}
