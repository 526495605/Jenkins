package com.ykyy.server.provider;

import com.ykyy.server.bean.PurchaseBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

@Slf4j
public class PurchaseProvider
{
    public String addPurchase(Integer child_id, Integer product_id,Integer users_id)
    {
        Long time = System.currentTimeMillis();
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
        log.info(sql);
        return sql;
    }

    public String addUserComment(Integer purchase_id, String comment)
    {
        Long time = System.currentTimeMillis();
        String sql = new SQL(){
            {
                UPDATE("purchase");
                SET("users_comment = " + comment);
                SET("users_comment_time = "+time);
                WHERE("purchase_id = "+purchase_id);
                WHERE("purchase_status = 3");
            }
        }.toString();
        log.info(sql);
        return sql;
    }
    public String addChildComment(Integer purchase_id, String comment)
    {
        Long time = System.currentTimeMillis();
        String sql = new SQL(){
            {
                UPDATE("purchase");
                SET("child_comment = " + comment);
                SET("child_comment_time = "+time);
                WHERE("purchase_id = "+purchase_id);
                WHERE("purchase_status = 3");
            }
        }.toString();
        log.info(sql);
        return sql;
    }
}
