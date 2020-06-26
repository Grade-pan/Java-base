package JDBC;

import JDBC.Bean.User;
import com.alibaba.fastjson.JSON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/11
 */
public class Test {
    public static void main(String[] args) {
        Dao dao = new DaoImpl();
//        List<Access> weather = new ArrayList<>();
//        weather = dao.getALl();
//        for (Access access : weather) {
//            System.out.println(access.getAddress());
//            System.out.println(access.getTemp());
//            System.out.println(access.getWindDirection());
//            System.out.println(access.getWindPower());
//            System.out.println(access.getHumidity());
//            System.out.println(access.getReportTime());
//        }

//        String sql = "select email,age from user where SuperRoot=?";
//        try {
//            List list1 = new ArrayList();
//            list1.add(1);
//            List<Map<String, Object>> list = dao.findModeResult(sql, list1);
//            System.out.println(list);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        String sql2 = "update user set email=? where username=?";
//        List<Object> list2 = new ArrayList<>();
//        list2.add("ssfsfsf@163.com");
//        list2.add("小明");
//        String information1 = dao.update(sql2, list2);
//        if (information1.equals("用户名已被注册")) {
//            information1 = "修改密码失败";
//        }
//        System.out.println(information1);
//
//        int result = dao.Count("SELECT COUNT(*) FROM user ");
//        System.out.println(result);
//        String sql3 = "select * from user where username=?";
//        List<Object> params = new ArrayList<>();
//        params.add("小明");
//        User user;
//        try {
//            user = dao.findSimpleRefResult(sql3, params, User.class);
//            System.out.println(user.getSex());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String sql4 = "select * from user";
        List<Object> params = new ArrayList<>();
        params.add("小明");
        params.add("千古");
        params.add("小红");
        List<User> result;
        List<User> userList = new ArrayList<>();
        try {
            result = dao.findMoreRefResult(sql4, null, User.class);
            for (User user1 : result) {
                User user = new User();
                user.setUsername(user1.getUsername());
                user.setEmail(user1.getEmail());
                user.setPassword(user1.getPassword());
                user.setAge(user1.getAge());
                user.setTel(user1.getTel());
                user.setAddress(user1.getAddress());
                user.setIp(user1.getIp());
                user.setSex(user1.getSex());
                user.setSuperRoot(user1.getSuperRoot());
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("message", "ok");
        map.put("code", 1);
        map.put("data", userList);
        map.put("count", userList.size());
        System.out.println(JSON.toJSONString(map));
    }
}
