package JDBC;/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public boolean FoundInformation(int i, String userName, String userPass) {
        String sql = "select ID from UserLogin where userName=? and userPass=?";
        return this.executeSql(sql, i, userName, userPass);
    }

    @Override
    public boolean RegisterUser(int i, String userName, String userPass, String telephone, String email) {
        String sql = "insert into UserLogin (userName,userPass,telephone,email) values(?,?,?,?)";
        return this.executeSql(sql, i, userName, userPass, telephone, email);

    }
}
