package com.ykyy.server.service;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ProductBean;

import java.util.List;

public interface ProductService
{
    Integer addProduct(ProductBean productBean);

    List<ProductBean> getProduct();

    Integer deleteProductById(Integer product_id);

    ProductBean getProductById(Integer product_id);

    ProductBean UpdateProduct(ProductBean productBean);

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    List<CategoryBean> getProductCategory(Integer product_id);

    Integer insertProductCategory(Integer product_id, Integer[] category_id);

    Integer deleteProductCategoryaAll(Integer product_id);

    Integer deleteProductCategoryById(Integer id);
}
