package Root.news;

import JDBC.Bean.resultJson;
import JDBC.Dao;
import JDBC.DaoImpl;
import Root.bean.analysis;
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
 * Date: 2020/6/25
 */
@WebServlet(name = "quickSendNews", urlPatterns = "/quickNews")
public class quickSendNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> list = new ArrayList<>();
        list.add("title");
        list.add("author");
        list.add("imgUrl");
        list.add("content");
        list.add("postTime");
        analysis analysis = new analysis(request.getReader(), list);
        Map<Object, Object> mapAttr;
        mapAttr = analysis.Username();

        Dao dao = new DaoImpl();
        String sql = "insert into swpnews(title,author,type,date,content,imgUrl) values(?,?,?,?,?,?) ";
        List<Object> params = new ArrayList<>();
        params.add(mapAttr.get("title"));
        params.add(mapAttr.get("author"));
        params.add("实时");
        params.add(mapAttr.get("postTime"));
        params.add(mapAttr.get("content"));
        params.add(mapAttr.get("imgUrl"));
        String information = dao.update(sql, params);

        resultJson resultJson = new resultJson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(resultJson.operator(information));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
