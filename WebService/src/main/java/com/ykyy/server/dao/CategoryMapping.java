package com.ykyy.server.dao;

import com.ykyy.server.bean.CategoryBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryMapping
{
    @Insert("INSERT category (category_name) VALUE (#{category_name})")
    Integer addGateory(CategoryBean categoryBean);

    @Delete("UPDATE category SET category_status=0 WHERE category_status=1 and category_id = #{0}")
    Integer deleteGeteory(Integer category_id);

    @Select("SELECT category_id, category_name FROM category WHERE category_status=1")
    List<CategoryBean> getCategory();

    @Select("SELECT category_id, category_name FROM category WHERE category_status=1 and category_id=#{0}")
    CategoryBean getCategoryById(Integer category_id);

    @Update("UPDATE category SET category_name=#{1} WHERE category_id=#{0} and category_status=1")
    Integer updateCategory(Integer category_id, String category_name);
}
