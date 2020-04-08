package chap05;

import chap05.JDBC.UserDao;
import chap05.JDBC.UserDaoImpl;

import javax.swing.*;
import java.sql.*;

public class register extends Thread {
    private static JFrame f = new JFrame("注册界面");
    private static JPanel jp = new JPanel();
    private static JTextField User_field = new JTextField();
    private static JTextField Pwd_field = new JTextField();
    private static JButton register_button = new JButton("注册");
    private static Statement s;
    private static Connection c;

    public register() {
    }

    @Override
    public void run() {
        //UI
        UI();
        //事件处理
        register_button.addActionListener(e -> {
            UserDao userDao = new UserDaoImpl();
            try {
                userDao.register(1, User_field.getText(), Cryptography.getHash(Pwd_field.getText(), "sha-256"));
                f.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    private static void UI() {
        jp.setLayout(null);
        JLabel label = new JLabel("用户名");
        JLabel label1 = new JLabel("密码");
        label.setBounds(30, 30, 100, 40);
        label1.setBounds(30, 100, 100, 40);
        User_field.setBounds(160, 30, 200, 40);
        Pwd_field.setBounds(160, 100, 200, 40);
        register_button.setBounds(280, 200, 100, 40);
        jp.add(label);
        jp.add(label1);
        jp.add(User_field);
        jp.add(Pwd_field);
        jp.add(register_button);
        f.add(jp);
        f.setBounds(0, 0, 400, 400);
        f.setVisible(true);
    }
}
