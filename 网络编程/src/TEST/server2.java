package TEST;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class server2 {
    public static void main(String[] args) {
        //GUI 部分
        String flag="not null";
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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            ServerSocket ss = new ServerSocket(8080);
            Socket s = ss.accept();
            //获取IP和端口号
            jt1.setText(String.valueOf(s.getLocalSocketAddress()));


            //向客户端发送内容

            while(!flag.equals("exit")) {
                jb1.addActionListener(e -> {
                    try {
                        DataOutputStream dos = null;
                        text[0] = methodA(jt);
                        System.out.println(text[0]);
                        OutputStream os = s.getOutputStream();
                        dos = new DataOutputStream(os);
                        if(!text[0].equals("")) {
                            methodB(text[0], jTextArea, df);
                            dos.writeUTF(text[0]);
                            methodC(jt);
                        }else{
                            System.out.println("输出空");
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                //读取客户端内容
                DataInputStream dis = null;
                String msg = null;
                InputStream is = s.getInputStream();
                dis = new DataInputStream(is);
                msg = dis.readUTF();
                jTextArea.append(df.format(new Date()) + "  客户端:" + msg + "\r\n");
                System.out.println(msg);
            }
            s.close();
            ss.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //监听实现函数

    //整体退出聊天
    private static void exit() {
        System.exit(0);
    }
    //获取输入内容
    private static String methodA(JTextField jf1) {
        String s = jf1.getText();
        return s;
    }
    //将输入内容输出到文本域
    private static void methodB(String str, JTextArea textArea, SimpleDateFormat df) {
        textArea.append(df.format(new Date()) + "  服务端:" + str + "\r\n");
    }
    //将输入内容清空
    private static void methodC(JTextField jf1) {
        jf1.setText("");
    }
}
