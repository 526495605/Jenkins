package com.ykyy.server.provider;

import com.ykyy.server.bean.ProductBean;
import com.ykyy.server.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

@Slf4j
public class ProductProvider
{
    public String addProduct(ProductBean productBean)
    {
        Long time = new Date().getTime();
        String sql ="INSERT product(product_name, product_from, product_to, product_theme, product_url, product_money, product_time) VALUE (#{product_name}, #{product_from}, #{product_to}, #{product_theme}, #{product_url}, #{product_money}, "+time+")";
       log.info(sql);
        return sql;
    }

    public String updateProduct(ProductBean productBean)
    {
        String sql = new SQL()
        {
            {
                UPDATE("product");
                if(null!=productBean.getProduct_name())
                {
                    SET("product_name=#{product_name}");
                }
                if(null!=productBean.getProduct_from())
                {
                    SET("product_from=#{product_from}");
                }
                if(null!=productBean.getProduct_to())
                {
                    SET("product_to=#{product_to}");
                }
                if(null!=productBean.getProduct_theme())
                {
                    SET("product_theme=#{product_theme}");
                }
                if(null!=productBean.getProduct_url())
                {
                    SET("product_url=#{product_url}");
                }
                if(null!=productBean.getProduct_money())
                {
                    SET("product_money=#{product_money}");
                }
                if(null!=productBean.getProduct_id())
                {
                    WHERE("product_id=#{product_id} and product_status=1");
                }
                else
                {
                    throw Exceptions.get409Exception("没有输入product_id");
                }
            }
        }.toString();
        log.info("ProductProvider:  "+sql);
        return sql;
    }

}
