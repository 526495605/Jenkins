package com.ykyy.server.service;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.dao.CategoryMapping;

import java.util.List;

public interface CategoryService
{
    Integer addGateory(CategoryBean categoryBean);

    Integer deleteGeteory(Integer category_id);

    List<CategoryBean> getCategory();

    CategoryBean getCategoryById(Integer category_id);

    CategoryBean updateCategory(Integer category_id, String category_name);

}
/*
*  @Insert("INSERT category (category_name) VALUE (#{category_name})")
    Integer addGateory(CategoryMapping categoryMapping);

    @Delete("UPDATE category SET category_status=0 WHERE category_status=1 and category_id = #{0}")
    Integer deleteGeteory(Integer category_id);

    @Select("SELECT category_id, category_name FROM category WHERE category_status=1")
    List<CategoryBean> getCategory();

    @Select("SELECT category_id, category_name FROM category WHERE category_status=1 and category_id={0}")
    CategoryBean getCategoryById(Integer category_id);

    @Update("UPDATE category SET category_name=#{1} WHERE category_id=#{0} and category_status=1")
    Integer updateCategory(Integer category_id, String category_name);
*
*
* */