package com.ykyy.server.provider;

import com.ykyy.server.bean.UserBean;
import org.apache.ibatis.jdbc.SQL;


public class UserProvider
{

    public String delete(UserBean userBean)
    {
        String sql =   new SQL(){{
            DELETE_FROM("users");
            if(userBean==null || (userBean.getId()==null  && userBean.getWx()==null))
            {
                WHERE("1=-1");
            }
            if(null != userBean.getId()){
                WHERE("users_id= #{id}");
            }
            if(null != userBean.getWx())
            {
                WHERE("users_wx = #{wx}");
            }
        }}.toString();
        System.out.println("MySQL++++++ " + sql);
        return sql;
    }

    public String updateUser(UserBean userBean)
    {
        String sql = new SQL(){
            {
                UPDATE("users");
                if (null != userBean.getWx())
                {
                    SET("users_wx=#{wx}");
                }
                if (null != userBean.getName())
                {
                    SET("users_name=#{name}");
                }
                if (null != userBean.getPhone())
                {
                    SET("users_phone=#{phone}");
                }
                if (null != userBean.getId())
                {
                    WHERE("users_id=#{id}");
                }
                else
                {
                    WHERE("-1=1");
                }
        }}.toString();
        System.out.println("MySQL++++++ " + sql);
        return sql;
    }
}