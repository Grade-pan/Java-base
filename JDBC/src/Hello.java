import java.sql.*;

public class Hello {
    public static void main(String[] args) {
        Connection c = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Java?characterEncoding=utf8&useSSL=false&serverTimezone=UTC",
                    "root", "123456");
            System.out.println("数据库连接成功");


            s = c.createStatement();
            System.out.println("获取Statement对象：" + s);

            String sql = "insert into hero values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";
            for (int i = 0; i < 6; i++) {
                s.execute(sql);
            }
            //删除
//            sql = "delete from hero where id=126";
//            s.execute(sql);

            //修改
            sql = "update hero set name ='name 5' where id=3";
            s.execute(sql);
            System.out.println("数据插入成功");

            //查询
            sql = "select name from hero where hp=313";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
//                int id = rs.getInt("id");
                String name = rs.getString(1);
//                float hp = rs.getFloat("hp");
//                int damage = rs.getInt(4);
                System.out.printf("%s", name);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
