package com.ykyy.server.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

public class PurchaseProvider
{
    public String addPurchase(Integer users_id, Integer child_id, Integer product_id)
    {
        Long time = new Date().getTime();
        String sql = new SQL()
        {
            {
                INSERT_INTO("purchase");
                VALUES("users_id", String.valueOf(users_id));
                VALUES("child_id", String.valueOf(child_id));
                VALUES("product_id", String.valueOf(product_id));
                VALUES("purchase_time", String.valueOf(time));
            }
        }.toString();

        return sql;
    }
}
