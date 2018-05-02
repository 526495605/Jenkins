package com.ykyy.server.service.imp;

import com.ykyy.server.bean.CategoryBean;
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

    @Override
    public List<CategoryBean> getProductCategory(Integer product_id)
    {
        return productMapping.getProductCategory(product_id);
    }

    @Override
    public Integer insertProductCategory(Integer product_id, Integer[] category_id)
    {
        int k = 0;
        for(int i = 0; i<category_id.length; i++)
        {
            k += productMapping.insertProductCategory(product_id, category_id[i]);
        }
        return k;
    }

    @Override
    public Integer deleteProductCategoryaAll(Integer product_id)
    {
        return productMapping.deleteproductCategoryaAll(product_id);
    }

    @Override
    public Integer deleteProductCategoryById(Integer id)
    {
        return productMapping.deleteProductCategoryById(id);
    }

}
