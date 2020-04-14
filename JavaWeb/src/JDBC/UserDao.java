package JDBC;/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/


public interface UserDao {
    //查询
    boolean FoundInformation(int i, String userName, String userPass);

    //注册
    boolean RegisterUser(int i, String userName, String userPass, String telephone, String email);
}
