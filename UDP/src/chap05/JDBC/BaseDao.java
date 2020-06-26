package chap05.JDBC;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static boolean flag = false;


    //加载驱动
    static {
        try {
            Properties properties = new Properties();//创建对象
            InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(is);
            driver = properties.getProperty("DRIVER_CLASS");
            url = properties.getProperty("DB_URL");
            user = properties.getProperty("DB_USER");
            password = properties.getProperty("DB_PASSWORD");
            Class.forName(driver);
        } catch (ClassNotFoundException | IOException e) {
            JOptionPane.showMessageDialog(null, "驱动加载失败", "警告", JOptionPane.ERROR_MESSAGE);
        }
    }

    //连接数据库
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //关闭连接
    public static void closeALL(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    //sql执行
    public boolean executeSQL(int status, String preparedSql, Object... param) {//Object...可变参数，适用于方法的重构
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();//获取连接
            pstmt = conn.prepareStatement(preparedSql);//获取PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]);//预编译SQL设置参数
                }
            }
            //System.out.println("执行的sql语句是" + preparedSql);
            if (status == 0) {//全查询
                ResultSet rs = pstmt.executeQuery();//执行SQL语句
                while (rs.next()) {
                    flag = true;
                }
            }
            if (status == 1) {//插入
                pstmt.execute();
            }
            if (status == 2) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    JOptionPane.showMessageDialog(null, rs.getString("user_pwd"), "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeALL(conn, pstmt, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

}

