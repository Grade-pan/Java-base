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
import java.net.URLDecoder;

/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
  Date: (new Date()).toString()
*/
@WebServlet(urlPatterns = "/readcookie", name = "hellocookie")
public class hellocookie extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        response.setContentType("text/html;charset:utf-8");
        PrintWriter out = response.getWriter();

        if (cookies != null) {
            out.println("Cookie的名称和值");
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if ((cookie.getName()).compareTo("name") == 0) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    out.println("已删除的cookie:" + cookie.getName());
                }
                out.println("名称:" + cookie.getName());
                out.println("url值" + URLDecoder.decode(cookie.getValue(), "utf-8"));
            }
        } else {
            out.println("NO cookies found");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
