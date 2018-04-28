package com.ykyy.server.dao;

import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.provider.ChildProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface ChildMapping
{
    @UpdateProvider(type = ChildProvider.class,method = "updateChild")
    Integer updateChild(ChildBean childBean);

    @Insert("INSERT child{users_id, child_name, child_age, child_sex, child_height, child_nation, child_tel, child_grade, child_idcard, child_idcardnum, child_health, child_healthinfo, child_father_name, child_father_tel, child_father_idcard, child_mother_name, child_mother_tel, child_mother_idcard} VALUE(#{users_id} #{child_name}, #{child_age}, #{child_sex}, #{child_height}, #{child_nation}, #{child_tel}, #{child_grade}, #{child_idcard}, #{child_idcardnum}, #{child_health}, #{child_healthinfo}, #{child_father_name}, #{child_father_tel}, #{child_father_idcard}, #{child_mother_name}, #{child_mother_tel}, #{child_mother_idcard})")
    Integer addChild(ChildBean childBean);

    @Update("UPDATE child SET child_status=0 WHERE users_id=#{users_id} and child_id=#{child_id}")
    Integer deletChild(Integer users_id, Integer child_id);

}
