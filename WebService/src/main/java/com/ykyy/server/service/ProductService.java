package com.ykyy.server.service;

import com.ykyy.server.bean.ProductBean;

import java.util.List;

public interface ProductService
{
    Integer addProduct(ProductBean productBean);

    List<ProductBean> getProduct();

    Integer deleteProductById(Integer product_id);

    ProductBean getProductById(Integer product_id);

    ProductBean UpdateProduct(ProductBean productBean);
}

/*
*  @InsertProvider(type = ProductProvider.class, method = "addProduct")
    Integer addProduct(ProductBean productBean);

    @Select("SELECT product_id, product_name, product_from, product_to, product_theme, product_url, prodcct_money FROM product WHERE product_status=1")
    List<ProductBean> getProduct();

    @Select("SELECT product_id, product_name, product_from, product_to, product_theme, product_url, prodcct_money FROM product WHERE product_status=1 and product_id=#{0}")
    ProductBean getProductById(Integer product_id);

    @Delete("UPDATE product SET product_status=0 WHERE product_status=1 and product_id=#{0}")
    Integer deleteProductById(Integer product_id);

    @UpdateProvider(type = ProductProvider.class, method = "updateProduct")
    Integer UpdateProduct(ProductBean productBean);
*
* */
