package chap05;

import chap05.JDBC.UserDao;
import chap05.JDBC.UserDaoImpl;

import javax.swing.*;
import java.sql.*;

public class getPasssword extends Thread {
    private String User_id;

    public getPasssword(String User_id) {
        this.User_id = User_id;
    }

    @Override
    public void run() {
        if (User_id.equals("")) {
            JOptionPane.showMessageDialog(null, "请输入用户名", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        UserDao userDao = new UserDaoImpl();
        try {
            userDao.findPassword(2, User_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
