package com.feign.demo;

import org.springframework.stereotype.Component;

@Component
public class HiHystrix implements EruekaClientFeign
{
    @Override
    public String sayHiFromClientEureka(String name)
    {
        return "hi "+ name + " sorry, error";
    }
}
