import java.nio.channels.ClosedSelectorException;
import java.sql.*;

public class PSStatment {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "insert into hero values(null,?,?,?)";
        String sql0 = "select * from hero";
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Java?characterEncoding=utf8&useSSL=false&serverTimezone=UTC", "root", "123456");
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            for (int i = 0; i < 10; i++) {
                ps.setString(1, "提莫");
                ps.setFloat(2, 313.0f);
                ps.setInt(3, 50);
                ps.execute();
            }
            System.out.println("数据插入成功");

            ResultSet rs = ps.executeQuery(sql0);
            while (rs.next()) {
                String heroName = rs.getString("name");
                int id = rs.getInt(1);
                Float hp = rs.getFloat("hp");
                int damage = rs.getInt("damage");
                System.out.println(id + "英雄名称：" + heroName + "  " + "血量：" + hp + "  " + "伤害：" + damage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
