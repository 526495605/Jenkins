package com.ykyy.server.dao;

import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.ProductBean;
import com.ykyy.server.provider.ChildProvider;
import com.ykyy.server.provider.UserProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface ChildMapping
{
    /**
    * @Author:owen
    * @Description:添加儿童
    * @Date:Create in 20:38 2018/4/23
    * @Modified By:
    */
    int add(ChildBean childBean);


    /**
    * @Author:owen
    * @Description:查询userid的儿童个数
    * @Date:Create in 20:39 2018/4/23
    * @Modified By:
    */
    @Select("SELECT count(*) FROM child where users_id = #{users_id}")
    int getChildCountByUserId(int users_id);

    /**
    * @Author:owen
    * @Description:查询userid的所有儿童
    * @Date:Create in 20:44 2018/4/23
    * @Modified By:
    */
    @Select("SELECT * FROM child WHERE users_id = #{users_id}")
    ChildBean[] getChildByUserId(int users_id);


    /**
    * @Author:owen
    * @Description:查询产品报名人数
    * @Date:Create in 20:39 2018/4/23
    * @Modified By:
    */
    @Select("SELECT count(*) FROM child where product_id = #{product_id}")
    int getChildCountByProductId(int product_id);

    /**
    * @Author:owen
    * @Description:查询产品报名的所有孩子
    * @Date:Create in 20:40 2018/4/23
    * @Modified By:
    */
    @Select("SELECT * FROM child WHERE product_id = #{product_id}")
    ChildBean[] getChildByProductId(int product_id);

    /**
    * @Author:owen
    * @Description:查询该孩子报的所有产品
    * @Date:Create in 20:41 2018/4/23
    * @Modified By:
    */

    ProductBean[] getProductByChildId(int child_id);

    /**
    * @Author:owen
    * @Description:更新儿童
    * @Date:Create in 20:46 2018/4/23
    * @Modified By:
    */
    @UpdateProvider(type = ChildProvider.class, method = "update")
    int UpdateChild(ChildBean childBean);

    /**
    * @Author:owen
    * @Description:删除儿童
    * @Date:Create in 20:47 2018/4/23
    * @Modified By:
    */
    @Update("UPDATE child SET child_status=0 WHERE users_id = #{users_id} and child_id = #{child_id}")
    int deleteChild(int users_id, int child_id);
}
