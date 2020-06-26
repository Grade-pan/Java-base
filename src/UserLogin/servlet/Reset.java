package UserLogin.servlet;

import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSON;

import javax.el.ArrayELResolver;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/17
 */
@WebServlet(name = "Reset", urlPatterns = "/reset")
public class Reset extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String verifyNumber = (String) session.getAttribute("code");//验证码
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String yanzhengma = request.getParameter("yanzhengma");
        Map<Object, Object> map = new HashMap<>();

        if (yanzhengma.toLowerCase().equals(verifyNumber.toLowerCase())) {
            Dao dao = new DaoImpl();
            String sql = "update user set password=? where email=?";
            List<Object> params = new ArrayList<>();
            params.add(password);
            params.add(email);
            String information = dao.update(sql, params);

            if (!information.equals("success")) {
                map.put("code", "404");
                map.put("messages", "重置密码失败");
            } else {
                map.put("code", "200");
                map.put("messages", "重置密码成功");
            }
        } else {
            map.put("code", 404);
            map.put("messages", "验证码错误");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(JSON.toJSONString(map));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
