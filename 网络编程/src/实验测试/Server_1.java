package 实验测试;


import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server_1 {

    public static void main(String args[]) throws IOException {
        //GUI 部分
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String flag = "not null";
        String[] text = {""};
        JFrame f = new JFrame();
        f.setTitle("TCP服务端");
        f.setBounds(0, 0, 500, 500);
        //定义面板
        JPanel jp1 = new JPanel();
        //定义按钮
        JButton jb1 = new JButton("发送");
        jp1.add(jb1);
        JButton jb2 = new JButton("退出");
        JLabel jl = new JLabel("端口号和IP地址");
        //定义文本框
        JTextField jt = new JTextField(15);
        JTextField jt1 = new JTextField(10);
        jp1.add(jt);
        jp1.add(jl);
        jp1.add(jt1);
        //定义文本域
        JTextArea jTextArea = new JTextArea(25, 40);
        //监听实现
        jb2.addActionListener(e -> exit());
        //向面板添加内容
        jp1.add(jTextArea);
        jp1.add(jb2);
        f.add(jp1);
        f.setVisible(true);

        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        jt1.setText(String.valueOf(s.getLocalSocketAddress()));
//        jb1.addActionListener(e -> {
//            text[0] = GetText(jt);
//            if (!text[0].equals("")) {
//                Send send = new Send(s);
//                Thread thread = new Thread(send);
//                thread.setName(df.format(new Date()) + "  服务端:  " + text[0] + "\r\n");
//                jTextArea.append(df.format(new Date()) + "  服务端:  " + text[0] + "\r\n");
//                thread.start();
//
//                new Thread(new Recieved(s)).start();
//                jTextArea.append(Recieved.Flag);
//                ClearText(jt);
//            } else {
//                System.out.println("输出空");
//            }
//
        new Thread(new Recieved(s)).start();
    }

    private static void exit() {
        System.exit(0);
    }

    private static String GetText(JTextField jt) {
        return jt.getText();
    }

    private static void ClearText(JTextField jt) {
        jt.setText("");
    }
}

