package com.ykyy.server.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.dao.ChildMapping;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
public class ChildServiceImp implements ChildService
{

    @Autowired
    ChildMapping childMapping;

    @Override
    public ChildBean updateChild(ChildBean childBean)
    {
        Integer result = childMapping.updateChild(childBean);
        if(result==null)
        {
            throw Exceptions.get404Exception("更新失败");
        }
        return getChildById(childBean.getUsers_id(), childBean.getChild_id());
    }

    @Override
    public String addChild(ChildBean childBean)
    {
        try
        {
            childMapping.addChild(childBean);
        }
        catch (DataAccessException e)
        {
            Throwable throwable = e.getCause();
            if(throwable instanceof MySQLIntegrityConstraintViolationException)
            {
                throw Exceptions.get409Exception("插入数据有误，用户id有误");
            }
        }
        return JSONObject.toJSON(childBean).toString();
    }

    @Override
    public Integer deletChild(Integer users_id, Integer child_id)
    {
        return childMapping.deletChild(users_id, child_id);
    }

    @Override
    public ChildBean getChildById(Integer users_id, Integer child_id)
    {
        ChildBean childBean = childMapping.getChildById(child_id);
        if(childBean==null)
        {
            throw Exceptions.get404Exception("child_id无法找到");
        }
        return childBean;
    }

    @Override
    public List<CategoryBean> getChildCategory(Integer child_id)
    {
        return childMapping.getChildCategory(child_id);
    }

    @Override
    public Integer insertChildCategory(Integer child_id, Integer[] category_id)
    {
        int k = 0;
        try
        {
            for(int i = 0; i<category_id.length; i++)
            {
                k += childMapping.insertChildCategory(child_id, category_id[i]);
            }
        }
        catch (DataAccessException e)
        {
            Throwable throwable = e.getCause();
            if(throwable instanceof  MySQLIntegrityConstraintViolationException)
            {
                throw Exceptions.get409Exception("插入错误，user_id或者child_id有误");
            }
        }
        return k;
    }

    @Override
    public Integer deleteChildCategoryaAll(Integer child_id)
    {
        return childMapping.deleteChildCategoryaAll(child_id);
    }

    @Override
    public Integer deleteChildCategoryById(Integer id)
    {
        return childMapping.deleteChildCategoryById(id);
    }

    @Override
    public Integer changeChildCategoryById(Integer child_id, Integer[] category_ids)
    {
        deleteChildCategoryaAll(child_id);
        Integer result = insertChildCategory(child_id,category_ids);
        return result;
    }


}
