package UserLogin.servlet;

import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSON;
import JDBC.Bean.*;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String UserName = request.getParameter("name");
        String psw = request.getParameter("password");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String tel = request.getParameter("telephone");
        String address = request.getParameter("address");
        String ip = request.getParameter("IP");
        String sex = request.getParameter("sex");

        Dao dao = new DaoImpl();
        String sql = "insert into user (username,email,password,age,tel,address,ip,sex,superRoot) values(?,?,?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(UserName);
        params.add(email);
        params.add(psw);
        params.add(age);
        params.add(tel);
        params.add(address);
        params.add(ip);
        params.add(sex);
        params.add("否");
        String information = dao.update(sql, params);
        resultJson resultJson = new resultJson();


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(resultJson.register(information));
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        this.doPost(request, response);

    }
}
