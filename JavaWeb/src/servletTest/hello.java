package servletTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;

/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/
@WebServlet(urlPatterns = "/hello", name = "hello")
public class hello extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie name = new Cookie("name", URLEncoder.encode(request.getParameter("name"), "utf-8"));
        Cookie url = new Cookie("url", request.getParameter("url"));
        //设置cookie过期时间
        name.setMaxAge(60 * 60 * 24);
        url.setMaxAge(60 * 60 * 24);

        response.addCookie(name);
        response.addCookie(url);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("name:" + request.getParameter("name"));
        out.println("url:" + url + request.getParameter("url"));
//        String name = new String(request.getParameter("name").getBytes("ISO_8859-1"), StandardCharsets.UTF_8);
//        out.println("网站名称:" + name);
//        out.println("网址:" + request.getParameter("url"));
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            out.println(paramName);
            String paramValues[] = request.getParameterValues(paramName);
            //读取单值数据
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                    out.println("空值");
                else
                    out.println(paramValue);
            } else {
                //读取多值数据
                for (int i = 0; i < paramValues.length; i++) {
                    out.println(paramValues[i]);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        response.setHeader("refresh", "3;url=index.jsp");//自动跳转
    }
}
