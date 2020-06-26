package Root.userManger;

import JDBC.Bean.resultJson;
import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSON;
import Root.bean.analysis;

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
 * Date: 2020/6/14
 */
@WebServlet(name = "delUser", urlPatterns = "/table/deleteUser")
public class delUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> list = new ArrayList<>();
        list.add("username");
        analysis analysis = new analysis(request.getReader(), list);
        Map<Object, Object> map1;
        map1 = analysis.Username();
        String username = (String) map1.get("username");
        //删除用户
        Dao dao = new DaoImpl();
        String sql = "delete from user where username=?";
        List<Object> params = new ArrayList<>();
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
