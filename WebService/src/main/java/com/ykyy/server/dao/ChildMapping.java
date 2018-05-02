package com.ykyy.server.dao;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.provider.ChildProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.DELETE;
import java.util.List;

public interface ChildMapping
{
    @UpdateProvider(type = ChildProvider.class,method = "updateChild")
    Integer updateChild(ChildBean childBean);

    @Insert("INSERT child (users_id, child_name, child_age, child_sex, child_height, child_nation, child_tel, child_grade, child_idcard, child_idcardnum, child_health, child_healthinfo, child_father_name, child_father_tel, child_father_idcard, child_mother_name, child_mother_tel, child_mother_idcard, child_school) VALUE(#{users_id},  #{child_name}, #{child_age}, #{child_sex}, #{child_height}, #{child_nation}, #{child_tel}, #{child_grade}, #{child_idcard}, #{child_idcardnum}, #{child_health}, #{child_healthinfo}, #{child_father_name}, #{child_father_tel}, #{child_father_idcard}, #{child_mother_name}, #{child_mother_tel}, #{child_mother_idcard}, #{child_school})")
    Integer addChild(ChildBean childBean);

    @Delete("UPDATE child SET child_status=0 WHERE users_id=#{0} and child_id=#{1}")
    Integer deletChild(Integer users_id, Integer child_id);

    @Select("SELECT users_id, child_id, child_name, child_age, child_sex, child_height, child_nation, child_tel, child_grade, child_idcard, child_idcardnum, child_health, child_healthinfo, child_father_name, child_father_tel, child_father_idcard, child_mother_name, child_mother_tel, child_mother_idcard, child_school FROM child WHERE users_id=#{0} and child_id=#{1} and child_status=1")
    ChildBean getChildById(Integer users_id, Integer child_id);

//    @Insert("INSERT child_category (child_id, category_id) VALUE (#{0}, #{1})")
//    Integer addChildCategory(Integer child_id, Integer category_id);

    @Select("SELECT category.category_id, category_name FROM category, child_category WHERE category.category_id=child_category.category_id and child_id = #{0}")
    List<CategoryBean> getChildCategory(Integer child_id);

    @Insert("INSERT child_category (child_id, category_id) VALUE (#{0}, #{1})")
    Integer insertChildCategory(Integer child_id, Integer category_id);

    @Delete("Delete FROM child_category WHERE child_id=#{0}")
    Integer deleteChildCategoryaAll(Integer child_id);

    @Delete("Delete FROM child_category WHERE child_category_id=#{0}")
    Integer deleteChildCategoryById(Integer id);

} 
