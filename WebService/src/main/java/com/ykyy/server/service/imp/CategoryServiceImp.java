package com.ykyy.server.service.imp;

import com.ykyy.server.bean.CategoryBean;
import com.ykyy.server.dao.CategoryMapping;
import com.ykyy.server.exception.Exceptions;
import com.ykyy.server.service.CategoryService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = false,rollbackFor = Exception.class)
public class CategoryServiceImp implements CategoryService
{

    @Autowired
    CategoryMapping categoryMapping;

    @Override
    public Integer addGateory(CategoryBean categoryBean)
    {
        Integer result = categoryMapping.addGateory(categoryBean);
        return result;
    }

    @Override
    public Integer deleteGeteory(Integer category_id)
    {
        Integer result = categoryMapping.deleteGeteory(category_id);
        return result;
    }

    @Override
    public List<CategoryBean> getCategory()
    {
        return categoryMapping.getCategory();
    }

    @Override
    public CategoryBean getCategoryById(Integer category_id)
    {
        return categoryMapping.getCategoryById(category_id);
    }

    @Override
    public CategoryBean updateCategory(Integer category_id, String category_name)
    {
        Integer result = categoryMapping.updateCategory(category_id, category_name);
        if(result==null || result == 0)
        {
            throw Exceptions.get404Exception("更新失败");
        }
        return getCategoryById(category_id);
    }
}
