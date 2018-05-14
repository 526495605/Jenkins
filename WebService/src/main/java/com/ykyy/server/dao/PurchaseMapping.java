package com.ykyy.server.dao;

import com.ykyy.server.bean.PurchaseBean;
import com.ykyy.server.provider.PurchaseProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.DataAccessException;

import javax.ws.rs.OPTIONS;
import java.util.List;

public interface PurchaseMapping
{

    @InsertProvider(type = PurchaseProvider.class, method = "addPurchase")
    Integer addPurchase(Integer child_id, Integer product_id,Integer users_id) throws DataAccessException;

    @Update("UPDATE purchase SET purchase_status = 2 WHERE purchase_id=#{0} and purchase_status=1")
    Integer addPurchaseSuss(Integer purchase_id);

    @Select("SELECT purchase_id, users_id, child_id, product_id, purchase_time, users_comment, users_comment_time, child_comment, child_comment_time, purchase_poster, product_sharemoney FROM purchase WHERE users_id=#{0} and purchase_status=#{1}")
    @Results
    (
        {
            @Result(column = "child_id", property = "childBean", one=@One(select = "com.ykyy.server.dao.ChildMapping.getChildById")),
            @Result(column = "product_id", property = "productBean", one=@One(select = "com.ykyy.server.dao.ProductMapping.getProductById"))
        }
    )
    List<PurchaseBean> getAllPurchase(Integer users_id, Integer purchase_status);

    @Update("UPDATE purchase SET purchase_status = 3 WHERE purchase_id=#{0} and purchase_status=2")
    Integer addPurchaseComplet(Integer purchase_id);

    @UpdateProvider(type = PurchaseProvider.class, method = "addUserComment")
    Integer addUserComment(Integer purchase_id, String comment);

    @UpdateProvider(type = PurchaseProvider.class, method = "addChildComment")
    Integer addChildComment(Integer purchase_id, String comment);

    @Select("SELECT purchase_id, users_id, child_id, product_id, purchase_time, users_comment, users_comment_time, child_comment, child_comment_time, purchase_poster, product_sharemoney, purchase_status FROM purchase WHERE purchase_id=#{0} and purchase_status!=0")
    @Results
            (
                    {
                            @Result(column = "child_id", property = "childBean", one=@One(select = "com.ykyy.server.dao.ChildMapping.getChildById")),
                            @Result(column = "product_id", property = "productBean", one=@One(select = "com.ykyy.server.dao.ProductMapping.getProductById"))
                    }
            )
    List<PurchaseBean> getPurchaseById(Integer purchase_id);
}












