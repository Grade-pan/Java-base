package chap05;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Login {
    static JTextField user_field;//用户名
    static JPasswordField password_field;//密码
    static JTextField host_field;//主机名
    static JTextField port_field;//端口
    static JButton register_button;//注册按钮
    static JButton getpwd_button;//招呼密码按钮
    static JButton login_button;//登录按钮

    public static void main(String[] args) {
        JFrame f = new JFrame("LOGIN——UI");
        JPanel jp = new JPanel();
        jp.setLayout(null);

        //图片标签
        JLabel picture_label = new JLabel();
        Icon icon = new ImageIcon("D:\\untitled\\UDP\\Picture\\qq.png");
        picture_label.setIcon(icon);
        picture_label.setBounds(20, 20, 170, 170);
        JLabel host_label = new JLabel("服务器主机名");
        JLabel port_label = new JLabel("服务器端口");
        host_label.setBounds(0, 420, 100, 27);
        port_label.setBounds(300, 420, 100, 27);

        //JTextFIELD,Jpasswordfield
        user_field = new JTextField();
        user_field.setBounds(210, 50, 180, 27);
        password_field = new JPasswordField();
        password_field.setBounds(210, 117, 180, 27);
        host_field = new JTextField("127.0.0.1");
        port_field = new JTextField("8080");
        host_field.setBounds(120, 420, 150, 27);
        port_field.setBounds(400, 420, 90, 27);

        //Jbutton
        register_button = new JButton("注册账号");
        register_button.setBounds(410, 53, 90, 22);
        getpwd_button = new JButton("找回密码");
        getpwd_button.setBounds(410, 120, 90, 22);
        login_button = new JButton("登录");
        login_button.setBounds(210, 210, 180, 27);

        //JCheckBox
        JCheckBox remeber = new JCheckBox("记住密码");
        JCheckBox autologin = new JCheckBox("自动登录");
        remeber.setBounds(208, 160, 90, 20);
        autologin.setBounds(310, 160, 90, 20);

        jp.add(picture_label);
        jp.add(host_label);
        jp.add(port_label);
        jp.add(user_field);
        jp.add(password_field);
        jp.add(host_field);
        jp.add(port_field);
        jp.add(register_button);
        jp.add(getpwd_button);
        jp.add(login_button);
        jp.add(remeber);
        jp.add(autologin);
        f.add(jp);
        f.setVisible(true);
        f.setBounds(0, 0, 520, 500);
        //登录
        new Login_start(f);
    }
}
