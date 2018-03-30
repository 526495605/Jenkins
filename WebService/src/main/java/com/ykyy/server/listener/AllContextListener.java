package com.ykyy.server.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class AllContextListener implements ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        log.info("开始listener++++++++++++++++++++++++");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {

    }
}
