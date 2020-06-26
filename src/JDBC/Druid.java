package JDBC;/*
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.*;
import java.util.Properties;

public class Druid {
    static DruidDataSource druidDataSource;

    static {
        Properties properties = new Properties();
        try {
            //加载配置文件
            properties.load(Druid.class.getClassLoader().getResourceAsStream("db.properties"));

            //初始化连接对象
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /*
//    获取连接对象
//     */
//    public static DruidDataSource getDruidDataSource() {
//        return druidDataSource;
//    }

    /*
    获取连接对象
     */
    public static Connection getConn() {
        try {
            return druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
