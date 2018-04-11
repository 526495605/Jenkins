package com.ribbon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController
{
    @Autowired
    private RibbonService ribbonService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/testRibbon")
    public String testRibbon()
    {
        ServiceInstance serviceInstance = loadBalancerClient.choose("erueka-client");
        return serviceInstance.getHost() + " : " +  serviceInstance.getPort();
    }


    @GetMapping("/hi")
    public String hi(@RequestParam String name)
    {
        return ribbonService.hi(name);
    }
}
