package chap05;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Login_start {
    private JFrame f;

    public Login_start(JFrame f) {
        this.f = f;
        //响应登录按钮
        Login.login_button.addActionListener(e -> {
            try {
                String id = Login.user_field.getText();
                String password = String.valueOf(Login.password_field.getPassword());
                if (id.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "帐号或密码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //获取服务器地址和端口
                String remoteName = Login.host_field.getText();
                InetAddress remoteAddr = InetAddress.getByName(remoteName);
                int remotePort = Integer.parseInt(Login.port_field.getText());
                //创建UDP套接字
                DatagramSocket clientSocket = new DatagramSocket();
                clientSocket.setSoTimeout(3000);//设置超时时间
                //构建用户登录消息
                Message msg = new Message();
                msg.setUserId(id);//登录名
                msg.setPassword(password); //密码
                msg.setType("M_LOGIN"); //登录消息类型
                msg.setToAddr(remoteAddr); //目标地址
                msg.setToPort(remotePort); //目标端口
                byte[] data = Translate.ObjectToByte(msg); //消息对象序列化
                //定义登录报文
                DatagramPacket packet = new DatagramPacket(data, data.length, remoteAddr, remotePort);
                //发送登录报文
                clientSocket.send(packet);

                //接收服务器回送的报文
                DatagramPacket backPacket = new DatagramPacket(data, data.length);
                clientSocket.receive(backPacket);
                clientSocket.setSoTimeout(0);//取消超时时间
                Message backMsg = (Message) Translate.ByteToObject(data);
                //处理登录结果
                if (backMsg.getType().equalsIgnoreCase("M_SUCCESS")) { //登录成功
                    //关闭上一个界面
                    f.dispose();
                    //身份验证成功，启动新界面
                    new Client_UI(clientSocket, msg,id);
                } else { //登录失败
                    JOptionPane.showMessageDialog(null, "用户ID或密码错误！\n\n登录失败！\n", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "登录错误", JOptionPane.ERROR_MESSAGE);
            }//end try

        });

        //响应注册按钮
        Login.register_button.addActionListener(e -> {
            new Thread(new register()).start();
        });

        //响应找回密码按钮
        Login.getpwd_button.addActionListener(e -> {
            String User_Id=Login.user_field.getText();
            new Thread(new getPasssword(User_Id)).start();
        });
    }
}
