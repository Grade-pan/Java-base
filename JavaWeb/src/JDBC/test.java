package JDBC;/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/

public class test {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.FoundInformation(0, "小明", "123456"));
        //System.out.println(userDao.RegisterUser(1, "王浩哲", "123456", "15036786341", "76650360@qq.com"));
    }
}
