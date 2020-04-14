package LoginAndRegister;

import JDBC.UserDao;
import JDBC.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/
@WebFilter(filterName = "FilterAll", urlPatterns = "/HTML/note.html")// /*原本为过滤所有，改为上述为的是不过滤图片和样式等内容，让HTML页面可以正常显示。
public class FilterAll implements Filter {


    public void destroy() {
        System.out.println("销毁过滤器");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("执行过滤操作");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String userName = "";//用户名
        String userPass = "";//用户密码

        userName = request.getParameter("user");
        userPass = request.getParameter("password");
        System.out.println(userName + userPass);

        UserDao userDao = new UserDaoImpl();
        if (!userName.equals("") && !userPass.equals("")) {
            if (userDao.FoundInformation(0, userName, userPass)) {
                chain.doFilter(request, response);
            } else {
                out.println("<html>\n" +
                        "<head><title>" + "验证失败" + "</title></head>\n" +
                        "<body bgcolor=\"#f0f0f0\">\n" +
                        "<h1 align=\"center\" style=\"color:red\"> " + " 用户名或密码错误,将在3秒后返回原页面! " + " </h1 >\n " +
                        "</body></html>");
                ((HttpServletResponse) response).setHeader("Refresh", "3;url=/JSPTEST2_war_exploded/HTML/Login.html");
                out.close();
            }
        } else {
            out.println("<html>\n" +
                    "<head><title>" + "验证失败" + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\" style=\"color:red\"> " + " 用户名和密码不能为空,将在3秒后返回原页面! " + " </h1 >\n " +
                    "</body></html>");
            ((HttpServletResponse) response).setHeader("Refresh", "3;url=/JSPTEST2_war_exploded/HTML/Login.html");
            out.close();
        }
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化过滤器");
    }

}
