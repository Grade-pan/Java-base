import java.sql.*;

public class Mysql_version {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java?characterEncoding=utf8&useSSL=false&serverTimezone=UTC", "root", "123456");
        ) {
            DatabaseMetaData dbmd = c.getMetaData();

            // 获取数据库服务器产品名称
            System.out.println("数据库产品名称:\t" + dbmd.getDatabaseProductName());
            // 获取数据库服务器产品版本号
            System.out.println("数据库产品版本:\t" + dbmd.getDatabaseProductVersion());
            // 获取数据库服务器用作类别和表名之间的分隔符 如test.user
            System.out.println("数据库和表分隔符:\t" + dbmd.getCatalogSeparator());
            // 获取驱动版本
            System.out.println("驱动版本:\t" + dbmd.getDriverVersion());

            System.out.println("可用的数据库列表：");
            // 获取数据库名称
            ResultSet rs = ((DatabaseMetaData) dbmd).getCatalogs();

            System.out.println();

            while (rs.next()) {
                System.out.println("数据库名称:\t" + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
