package JDBC;/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
  Date: (new Date()).toString()
*/

import java.sql.*;

public class JDBCTEST {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn=null;
        Statement smt=null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=UTC","root","123456");
        System.out.println(".........");
    }
}
