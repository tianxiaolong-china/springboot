package com.txl.demo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName: MyServletRequestListener
 * @Description: MyServletRequestListener类实现了ServletRequestListener接口，
 *                 因此可以对ServletRequest对象的创建和销毁这两个动作进行监听。
 * @author: 孤傲苍狼
 * @date: 2014-9-9 下午11:50:08
 *
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println(sre.getServletRequest() + "销毁了！！");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println(sre.getServletRequest() + "创建了！！");
    }
}
