package 实验测试;


import 多线程发送消息.Client;

import javax.swing.*;
import java.net.Socket;


public class Client_1 extends Client_UI implements WAY_UI {
    public Client_1() {
        jb3.addActionListener(e -> {
            try {
                Socket s = new Socket("127.0.0.1", 8080);
                String msg = WAY_UI.methodA(jc);
                String clientAddress = s.getInetAddress().getHostAddress();
                String IP_PORT = String.valueOf(s.getPort());
                System.out.println("客户端  " + msg + "   连接服务端    IP地址：" + clientAddress + " 端口号：" + IP_PORT + " 连接成功!");
                jte.append("客户端  " + msg + "   连接服务端    IP地址：" + clientAddress + " 端口号：" + IP_PORT + " 连接成功!" + "\r\n");
                jt1.setText(String.valueOf(s.getLocalSocketAddress()));

                //启动线程
                Client_Send_Receive client_send_receive = new Client_Send_Receive(s);
                Thread thread = new Thread(client_send_receive);
                thread.setName("客户端  " + msg);
                thread.start();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        Client_1 client_1 = new Client_1();
    }
}
