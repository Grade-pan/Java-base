package Root.rootInformation;

import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/20
 */
@WebServlet(name = "personalInformation", urlPatterns = "/personalInformation")
public class personalInformation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String getParameter = request.getQueryString();
        System.out.println(getParameter);
        String url = URLDecoder.decode(getParameter, "UTF-8");
        String username = url.split("=")[1];

        Dao dao = new DaoImpl();
        String sql = "select email,sex from user where username=?";
        List<Object> params = new ArrayList<>();
        params.add(username);

        System.out.println(username);

        Map<String, Object> map1;
        Map<String, Object> map = new HashMap<>();
        map1 = dao.findSimpleResult(sql, params);

        if (map1.get("email") == null) {
            map.put("code", "404");
        } else {
            map.put("code", "200");
            map.put("messages", map1.get("email"));
            map.put("sex",map1.get("sex"));
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(JSON.toJSONString(map));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
