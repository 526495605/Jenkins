package com.ykyy.server.service;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;

import java.util.List;

public interface ChildService
{

    ChildBean updateChild(ChildBean childBean);

    String addChild(ChildBean childBean);

    Integer deletChild(Integer users_id, Integer child_id);

    ChildBean getChildById(Integer users_id, Integer child_id);

    List<CategoryBean> getChildCategory(Integer child_id);

    Integer insertChildCategory(Integer child_id, Integer[] category_id);

    Integer deleteChildCategoryaAll(Integer child_id);

    Integer deleteChildCategoryById(Integer id);

    Integer changeChildCategoryById(Integer child_id, Integer[] category_ids);

}

