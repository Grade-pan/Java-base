package Root.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/25
 */
@WebServlet(name = "logOut", urlPatterns = "/logOut")
public class logOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("judge");
        request.getSession().invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(JSON.toJSONString(map));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
