package com.ykyy.server.service;

import com.ykyy.server.bean.ChildBean;

public interface ChildService
{

    ChildBean updateChild(ChildBean childBean);

    Integer addChild(ChildBean childBean);

    Integer deletChild(Integer users_id, Integer child_id);

    ChildBean getChildById(Integer users_id, Integer child_id);



}
