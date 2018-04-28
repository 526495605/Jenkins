package com.ykyy.server.provider;

import com.ykyy.server.bean.UserBean;
import com.ykyy.server.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

@Slf4j
public class UserProvider
{
    /*
    public String updateUser(UserBean userBean)
    {
        String sql = new SQL(){
            {
                UPDATE("users");
                if (null != userBean.getUsers_wx())
                {
                    SET("users_wx=#{wx}");
                }
                if (null != userBean.getUsers_name())
                {
                    SET("users_name=#{name}");
                }
                if (null != userBean.getUsers_phone())
                {
                    SET("users_phone=#{phone}");
                }
                if (null != userBean.getUsers_id())
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

    public String login(UserBean userBean)
    {
        String sql = new SQL()
        {
            {
                SELECT("COUNT(*)");
                FROM("users");
                WHERE("users_status =1 ");
                if(userBean.getUsers_name()!=null)
                {
                    WHERE("users_name = #{name} and users_password=#{password}");
                }
                else if(userBean.getUsers_phone()!=null)
                {
                    WHERE("users_phone = #{phone} and users_password=#{password}");
                }
                else
                {
                    WHERE("1=-1");
                }
            }
        }.toString();
        System.out.println(sql);
        return sql;
    }
    */
    public String updateUser(UserBean userBean)
    {
        String sql = new SQL(){
            {
                UPDATE("users");

                if(null != userBean.getUsers_name())
                {
                    SET("users_name=#{users_name}");
                }
                if(null != userBean.getUsers_wx())
                {
                    SET("users_wx=#{users_wx}");
                }
                if(null != userBean.getUsers_image())
                {
                    SET("users_image=#{users_image}");
                }
                if(null != userBean.getUsers_id())
                {
                    WHERE("users_id=#{users_id}");
                }
                else
                {
                    throw Exceptions.get404Exception("userid不存在");
                }
            }
        }.toString();
        log.info("UserProvider=  "+sql );
        return sql;
    }

    public String addUser(UserBean userBean)
    {
        Long time = new Date().getTime();
        System.out.println(time);
        String sql = new SQL()
        {
            {
                INSERT_INTO("users");
                VALUES("users_phone", "#{users_phone}");
                VALUES("users_password", "#{users_password}");
                VALUES("users_date",time.toString());
                if(null != userBean.getUsers_parent())
                {
                    VALUES("users_parent", "#{users_parent}");
                }
            }
        }.toString();

        log.info("UserProvider=  "+sql );
        return sql;
    }
}