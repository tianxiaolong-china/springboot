package com.txl.demo.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//测试拦截器1
public class LoginInterceptor implements HandlerInterceptor {

    //进入Handler方法之前执行
    //可以用于身份认证、身份授权。如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行，否则就放行
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        //获取请求的url
        String url = request.getRequestURI();
        //判断url是否公开地址（实际使用时将公开地址配置到配置文件中）
        //这里假设公开地址是否登陆提交的地址
        if(url.indexOf("login.action") > 0) {
            //如果进行登陆提交，放行
            return true;
        }

        //判断session
        HttpSession session = request.getSession();
        //从session中取出用户身份信息
        String username = (String) session.getAttribute("username");
        if(username == null) {
            return true;
        }

        //执行到这里表示用户身份需要验证，跳转到登陆页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    //节省空间，省略另外两个方法不写了，也不用处理
}