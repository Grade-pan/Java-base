package Root.news;

import JDBC.Bean.resultJson;
import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/22
 */
@WebServlet(name = "delNews", urlPatterns = "/delNews")
public class delNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID = request.getQueryString().split("/?ID=")[1];
        //删除用户
        Dao dao = new DaoImpl();
        String sql = "delete from swpnews where ID=?";
        List<Object> params = new ArrayList<>();
        params.add(Integer.parseInt(ID));
        String result = dao.update(sql, params);
        resultJson resultJson = new resultJson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(resultJson.operator(result));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
