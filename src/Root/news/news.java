package Root.news;

import JDBC.Bean.DivPage;
import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/22
 */
@WebServlet(name = "news", urlPatterns = "/news")
public class news extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getParameter = request.getQueryString();
        int count = 0;
        String countSQL = "select count(*) from swpnews";
        int[] limit;//返回结果数组

        JSONObject data = new JSONObject();
        Dao dao = new DaoImpl();
        count = dao.Count(countSQL);
        DivPage divPage = new DivPage(getParameter, count);
        limit = divPage.PageLimit();
        String sql = "select * from swpnews Limit " + limit[0] + "," + limit[1];

        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = dao.findModeResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        data.put("message", "ok");
        data.put("code", 0);
        data.put("count", count);
        data.put("data", list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(data.toJSONString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
