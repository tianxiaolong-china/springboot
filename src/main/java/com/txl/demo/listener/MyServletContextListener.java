package com.txl.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName: MyServletContextListener
 * @Description: MyServletContextListener类实现了ServletContextListener接口，
 *                 因此可以对ServletContext对象的创建和销毁这两个动作进行监听。
 * @author: 孤傲苍狼
 * @date: 2014-9-9 下午10:26:16
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象销毁");
    }
}