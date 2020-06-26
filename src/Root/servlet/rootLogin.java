package Root.servlet;

import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/18
 */
@WebServlet(name = "rootLogin", urlPatterns = "/rootLogin")
public class rootLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("userName");
        String password = request.getParameter("passWord");

        Dao dao = new DaoImpl();
        String sql = "select email,superRoot from user where username=? and password=?";
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);


        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map1 = dao.findSimpleResult(sql, params);

        if (map1.get("email") == null) {
            map.put("code", "404");
            map.put("messages", "用户名或密码验证失败");
        } else {
            if (map1.get("superRoot").equals("否")) {
                map.put("code", "404");
                map.put("messages", "非管理员无法登录");
            } else {
                map.put("code", "200");
                map.put("messages", "登录成功");

                //设置session
                HttpSession session = request.getSession();
                session.setAttribute("judge", "true");
                session.setMaxInactiveInterval(1800);//30分钟seesion过期

                //设置cookie
                Cookie cookie = new Cookie("rootname", username);
                cookie.setMaxAge(60 * 60 * 30);
                response.addCookie(cookie);
            }

        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(JSON.toJSONString(map));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
