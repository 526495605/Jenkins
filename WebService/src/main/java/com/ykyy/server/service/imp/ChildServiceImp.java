package com.ykyy.server.service.imp;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.bean.ChildBean;
import com.ykyy.server.dao.ChildMapping;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(result==null || result==0)
        {
            throw Exceptions.get404Exception("更新失败");
        }
        return getChildById(childBean.getUsers_id(), childBean.getChild_id());
    }

    @Override
    public Integer addChild(ChildBean childBean)
    {

        return childMapping.addChild(childBean);
    }

    @Override
    public Integer deletChild(Integer users_id, Integer child_id)
    {
        return childMapping.deletChild(users_id, child_id);
    }

    @Override
    public ChildBean getChildById(Integer users_id, Integer child_id)
    {
        return childMapping.getChildById(child_id);
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
        for(int i = 0; i<category_id.length; i++)
        {
            k += childMapping.insertChildCategory(child_id, category_id[i]);
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
}
