package com.ykyy.server.provider;

import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.bean.UserBean;
import com.ykyy.server.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class ChildProvider
{
    public String updateChild(ChildBean childBean)
    {
        String sql = new SQL()
        {
            {
                UPDATE("child");
                if(null != childBean.getChild_age())
                {
                    SET("child_age=#{child_age}");
                }
                if(null != childBean.getChild_father_name())
                {
                    SET("child_father_name=#{child_father_name}");
                }
                if(null != childBean.getChild_mother_name())
                {
                    SET("child_mother_name=#{child_mother_name}");
                }
                if(null != childBean.getChild_father_tel())
                {
                    SET("child_father_tel=#{child_father_tel}");
                }
                if(null != childBean.getChild_mother_tel())
                {
                    SET("child_mother_tel=#{child_mother_tel}");
                }
                if(null != childBean.getChild_grade())
                {
                    SET("child_grade=#{child_grade}");
                }
                if(null != childBean.getChild_health())
                {
                    SET("child_health=#{child_health}");
                }
                if(null != childBean.getChild_healthinfo())
                {
                    SET("child_healthinfo=#{child_healthinfo}");
                }
                if(null != childBean.getChild_idcard())
                {
                    SET("child_idcard=#{child_idcard}");
                }
                if(null != childBean.getChild_idcardnum())
                {
                    SET("child_idcardnum=#{child_idcardnum}");
                }
                if(null != childBean.getChild_height())
                {
                    SET("child_height=#{child_height}");
                }
                if(null != childBean.getChild_sex())
                {
                    SET("child_sex=#{child_sex}");
                }
                if(null != childBean.getChild_nation())
                {
                    SET("child_nation=#{child_nation}");
                }
                if(null != childBean.getChild_school())
                {
                    SET("child_school=#{child_school}}");
                }
                if(null != childBean.getChild_mother_idcard())
                {
                    SET("child_mother_idcard=#{child_mother_idcard}");
                }
                if(null != childBean.getChild_father_idcard())
                {
                    SET("child_father_idcard=#{child_father_idcard}");
                }
                if(null != childBean.getChild_tel())
                {
                    SET("child_tel=#{child_tel}");
                }
                System.out.println(childBean.getUsers_id() + "+++" + childBean.getChild_id());
                if((null != childBean.getUsers_id()) && (null != childBean.getChild_id()))
                {
                    WHERE("users_id=#{users_id} and child_id=#{child_id} and child_status=1");
                }
                else
                {
                    throw Exceptions.get404Exception("userid不存在");
                }
            }
        }.toString();
        log.warn("updateChild=  "+sql );
        return sql;
    }
}
