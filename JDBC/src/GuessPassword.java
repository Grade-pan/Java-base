import java.sql.*;

public class GuessPassword {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Java?characterEncoding=utf8&useSSL=false&serverTimezone=UTC",
                "root", "123456");
             Statement s = c.createStatement();
        ) {
            String name = "dashen";
            String password = "thisispassword";
            String sql = "select * from user where name= '" + name + "' and password ='" + password + "'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                System.out.println("账号密码正确");
            } else {
                System.out.println("账号密码不正确");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
