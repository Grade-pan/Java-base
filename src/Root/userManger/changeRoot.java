package Root.userManger;

import JDBC.Dao;
import JDBC.DaoImpl;
import Root.bean.analysis;
import com.alibaba.fastjson.JSON;
import JDBC.Bean.resultJson;

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
 * Date: 2020/6/14
 */
@WebServlet(name = "changeRoot", urlPatterns = "/table/changeRoot")
public class changeRoot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("username");
        list.add("superRoot");
        analysis analysis = new analysis(request.getReader(), list);
        Map<Object, Object> map1;
        map1 = analysis.Username();
        String username = (String) map1.get("username");
        String superRoot = (String) map1.get("superRoot");

        //更改用户权限
        Dao dao = new DaoImpl();
        String sql = "update user set superRoot=? where username=?";
        List<Object> params = new ArrayList<>();
        if (superRoot.equals("是")) {
            params.add("否");
        } else {
            params.add("是");
        }
        params.add(username);
        String result = dao.update(sql, params);
        resultJson resultJson = new resultJson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(resultJson.operator(result));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
