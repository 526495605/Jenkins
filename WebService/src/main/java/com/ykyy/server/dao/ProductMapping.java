package com.ykyy.server.dao;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ProductBean;
import com.ykyy.server.provider.ProductProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapping
{
    @InsertProvider(type = ProductProvider.class, method = "addProduct")
    Integer addProduct(ProductBean productBean);

    @Select("SELECT product_id, product_name, product_from, product_to, product_theme, product_url, product_money FROM product WHERE product_status=1")
    List<ProductBean> getProduct();

    @Select("SELECT product_id, product_name, product_from, product_to, product_theme, product_url, product_money FROM product WHERE product_status=1 and product_id=#{0}")
    ProductBean getProductById(Integer product_id);

    @Delete("UPDATE product SET product_status=0 WHERE product_status=1 and product_id=#{0}")
    Integer deleteProductById(Integer product_id);

    @UpdateProvider(type = ProductProvider.class, method = "updateProduct")
    Integer updateProduct(ProductBean productBean);

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Select("SELECT category.category_id, category_name FROM category, product_category WHERE category.category_id=product_category.category_id and product_id = #{0}")
    List<CategoryBean> getProductCategory(Integer product_id);

    @Insert("INSERT product_category (product_id, category_id) VALUE (#{0}, #{1})")
    Integer insertProductCategory(Integer product_id, Integer category_id);

    @Delete("Delete FROM product_category WHERE product_id=#{0}")
    Integer deleteproductCategoryaAll(Integer product_id);

    @Delete("Delete FROM product_category WHERE product_category_id=#{0}")
    Integer deleteProductCategoryById(Integer id);
}

















