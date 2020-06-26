package UserLogin.servlet;

import JDBC.Dao;
import JDBC.DaoImpl;
import JDBC.Bean.*;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/17
 */
@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Dao dao = new DaoImpl();
        String sql = "select email from user where username=? and password=?";
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);


        Map<String, Object> map1;
        map1 = dao.findSimpleResult(sql, params);
        resultJson resultJson = new resultJson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(resultJson.userLogin((String) map1.get("email")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
