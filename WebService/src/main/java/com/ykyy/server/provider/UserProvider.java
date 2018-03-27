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
}
