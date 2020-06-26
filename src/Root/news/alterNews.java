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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/23
 */
@WebServlet(name = "alterNews", urlPatterns = "/alterNews")
public class alterNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlParam = request.getQueryString();
        String nature = urlParam.split("&")[0].split("=")[0];//属性
        String value = URLDecoder.decode(urlParam.split("&")[0].split("=")[1], "UTF-8");//属性值
        String ID = urlParam.split("&")[1].split("=")[1];

        String sql = null;
        Dao dao = new DaoImpl();
        List<Object> params = new ArrayList<>();

        switch (nature) {
            case "title":
                sql = "update swpnews set title=? where ID=?";
                params.add(value);
                break;
            case "content":
                sql = "update swpnews set content=? where ID=?";
                params.add(value);
                break;
            case "date":
                sql = "update swpnews set date=? where ID=?";
                params.add(value);
                break;
            case "author":
                sql = "update swpnews set author=? where ID=?";
                params.add(value);
                break;
        }
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
