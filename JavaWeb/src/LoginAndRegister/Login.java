package LoginAndRegister;

import JDBC.User;
import JDBC.UserDao;
import JDBC.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
  Date: (new Date()).toString()
*/
@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String userName = "";
        String userPass = "";
        String telephone = "";
        String email = "";
        UserDao userDao = new UserDaoImpl();
        PrintWriter out = response.getWriter();

        userName = request.getParameter("userText");
        userPass = request.getParameter("PasswordText");
        telephone = request.getParameter("TelephoneText");
        email = request.getParameter("EmailText");

        if (!userName.equals("") && !userPass.equals("") && !telephone.equals("") && !email.equals("")) {
            userDao.RegisterUser(1, userName, userPass, telephone, email);//插入数据库
            response.setHeader("Refresh", "0,url=/JSPTEST2_war_exploded/HTML/Login.html");
            out.close();
        } else {
            out.println("上述内容不能为空");
            response.setHeader("Refresh", "3,url=/JSPTEST2_war_exploded/HTML/register.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
