package chap05.JDBC;

public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public boolean login(int i, String user, String password) throws Exception {
        String sql = "select ID from user_list where user_id=? and user_pwd=?";
        return this.executeSQL(i, sql, user, password);
    }

    @Override
    public void register(int i, String user, String password) throws Exception {
        String sql = "insert into user_list (user_id,user_pwd) values(?,?)";
        this.executeSQL(i, sql, user, password);
        System.out.println("插入成功");
    }

    @Override
    public void findPassword(int i, String user) throws Exception {
        String sql = "select user_pwd from user_list where user_id=?";
        this.executeSQL(i, sql, user);
    }
}
