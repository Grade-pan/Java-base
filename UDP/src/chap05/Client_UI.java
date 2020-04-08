package chap05;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client_UI extends Thread {
    private static JFrame f = new JFrame();
    private static JPanel panel1 = new JPanel();
    static JTextArea txtArea = new JTextArea();
    private static JTextArea txtInput = new JTextArea();
    private static JButton btnsend = new JButton("发送");
    private static JButton btnexit = new JButton("退出");
    private static JLabel label = new JLabel("会话消息窗口");
    private static JLabel label1 = new JLabel("发言窗口");
    private static JLabel label2 = new JLabel("在线用户");
    static JList userlist;
    static JScrollPane jScrollPane;
    private DatagramSocket clientSocket; //客户机套接字
    private Message msg; //消息对象
    private byte[] data = new byte[8096]; //8K字节数组
    private String id;

    public Client_UI(DatagramSocket socket, Message msg, String id) {
        this.id = id;//用户名
        UI(id);//启动新界面
        clientSocket = socket; //初始化会话套接字
        this.msg = msg; //登录消息
        new Thread(new ReceiveMessage(clientSocket, this)).start();// //创建客户机消息接收和处理线程
        //监听发送消息按钮函数
        btnsend.addActionListener(e -> {
            try {
                msg.setText(txtInput.getText());//获取输入的文本
                System.out.println(txtInput.getText());
                msg.setType("M_MSG"); //普通会话消息
                data = Translate.ObjectToByte(msg);//消息对象序列化
                //构建发送报文
                DatagramPacket packet = new DatagramPacket(data, data.length, msg.getToAddr(), msg.getToPort());
                clientSocket.send(packet); //发送
                txtInput.setText(""); //清空输入框
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
            }
        });
        //监听退出按钮
        btnexit.addActionListener(e -> {
            try {
                msg.setType("M_QUIT"); //消息类型
                msg.setText(null);
                data = Translate.ObjectToByte(msg); //消息对象序列化
                //构建发送
                DatagramPacket packet = new DatagramPacket(data, data.length, msg.getToAddr(), msg.getToPort());
                clientSocket.send(packet); //发送
                System.exit(0);
            } catch (IOException ex) {
            }
            clientSocket.close(); //关闭套接字
        });
    }

    private static void UI(String id) {
        panel1.setLayout(null);
        label.setBounds(0, 0, 100, 25);
        txtArea.setBounds(0, 30, 200, 200);
        label1.setBounds(0, 240, 100, 25);
        txtInput.setBounds(0, 290, 200, 200);
        btnsend.setBounds(100, 500, 80, 25);
        btnexit.setBounds(400, 500, 80, 25);
        label2.setBounds(220, 0, 100, 25);


        panel1.add(label);
        panel1.add(txtArea);
        panel1.add(label1);
        panel1.add(txtInput);
        panel1.add(btnsend);
        panel1.add(btnexit);
        panel1.add(label2);
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listModel.add(0, "QQ小冰");
        listModel.add(1, "中国知名作家刘信达");
        listModel.add(2, "黑猫警长giao哥skrGiaoSkrGiao我们一起叫");
        listModel.add(3, "中华**创始人 **");
        userlist = new JList(listModel);
        jScrollPane = new JScrollPane(userlist);
        jScrollPane.setBounds(220, 40, 260, 450);
        panel1.add(jScrollPane);
        f.add(panel1);
        f.setTitle(id);
        f.setVisible(true);
        f.setBounds(0, 0, 510, 580);
        f.setDefaultCloseOperation(3);
    }
}
