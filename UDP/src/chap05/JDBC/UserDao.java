package chap05.JDBC;

public interface UserDao {
    //验证登录
    boolean login(int i, String user, String password) throws Exception;//设立标志，判别查询，插入，删除，更新

    //注册
    void register(int i, String user, String password) throws Exception;

    //找回密码
    void findPassword(int i, String user) throws Exception;
}
