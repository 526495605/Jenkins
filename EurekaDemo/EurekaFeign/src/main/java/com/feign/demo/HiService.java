package com.feign.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HiService
{
    @Autowired
    EruekaClientFeign eruekaClientFeign;

    public String sayHi(String name)
    {
        return eruekaClientFeign.sayHiFromClientEureka(name);
    }

}
