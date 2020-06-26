//package servletTest;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///*
//  Created by IntelliJ IDEA.
//  User: 1817865166@qq.com
//*/
//@WebFilter(filterName = "LogFilter",urlPatterns = "/hello" )//拦截所有请求
//public class LogFilter implements Filter {
//    public void init(FilterConfig filterConfig) {
//
//        System.out.println("过滤器初始化");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("执行过滤操作");
//        // 把请求传回过滤链
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    public void destroy() {
//        System.out.println("销毁过滤器");
//        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
//    }
//}
