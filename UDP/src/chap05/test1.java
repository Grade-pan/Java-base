package chap05;


import javax.swing.*;
import java.sql.*;


public class test1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection c = null;
        Statement s = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Java?characterEncoding=utf8&useSSL=false&serverTimezone=UTC",
                "root", "123456");
        s = c.createStatement();
        System.out.println("获取Statement对象：" + s);
        String sql = "select user_id from user_list";
        ResultSet rs = s.executeQuery(sql);
        String user="小冰";
        Boolean flag=false;
        while (rs.next()) {
            String user_id = rs.getString(1);
            if(user.equals(user_id)){
               flag=true;
            }
        }
        System.out.println(flag);
    }
}
