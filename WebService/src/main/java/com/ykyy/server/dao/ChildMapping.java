package com.ykyy.server.dao;

import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.ProductBean;

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
    int getChildCountByUserId(int users_id);

    /**
    * @Author:owen
    * @Description:查询userid的所有儿童
    * @Date:Create in 20:44 2018/4/23
    * @Modified By:
    */
    ChildBean[] getChildByUserId(int users_id);


    /**
    * @Author:owen
    * @Description:查询产品报名人数
    * @Date:Create in 20:39 2018/4/23
    * @Modified By:
    */
    int getChildCountByProductId(int product_id);

    /**
    * @Author:owen
    * @Description:查询产品报名的所有孩子
    * @Date:Create in 20:40 2018/4/23
    * @Modified By:
    */
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
    int UpdateChild(ChildBean childBean);

    /**
    * @Author:owen
    * @Description:删除儿童
    * @Date:Create in 20:47 2018/4/23
    * @Modified By:
    */
    int deleteChild(int users_id, int child_id);
}
