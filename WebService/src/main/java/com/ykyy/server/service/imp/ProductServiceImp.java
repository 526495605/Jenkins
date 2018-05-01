package com.ykyy.server.service.imp;

import com.ykyy.server.bean.ProductBean;
import com.ykyy.server.dao.ProductMapping;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
public class ProductServiceImp implements ProductService
{
    @Autowired
    ProductMapping productMapping;

    @Override
    public Integer addProduct(ProductBean productBean)
    {
        return productMapping.addProduct(productBean);
    }

    @Override
    public List<ProductBean> getProduct()
    {
        return productMapping.getProduct();
    }

    @Override
    public Integer deleteProductById(Integer product_id)
    {
        return productMapping.deleteProductById(product_id);
    }

    @Override
    public ProductBean getProductById(Integer product_id)
    {
        return productMapping.getProductById(product_id);
    }

    @Override
    public ProductBean UpdateProduct(ProductBean productBean)
    {
        Integer result = productMapping.updateProduct(productBean);
        if(result==null || result == 0)
        {
            throw Exceptions.get404Exception("更新失败");
        }
        return getProductById(productBean.getProduct_id());
    }
}
