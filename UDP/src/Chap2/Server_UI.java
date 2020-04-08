package Chap2;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class Server_UI {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton start_Button;
    public JTextArea textArea1;
    private JButton EXIT_Button;
    private ServerSocket listenSocket = null;
    private Socket toClientSocket = null;
    public static int clientCounts = 0;//客户数量编号

    public Server_UI() {
        start_Button.addActionListener(e -> {
            try {
                start_Button.setEnabled(false);
                String hostName = textField1.getText();//主机名
                int hostPort = Integer.parseInt(textField2.getText());//端口
                SocketAddress serverAddr = new InetSocketAddress(InetAddress.getByName(hostName), hostPort);
                listenSocket = new ServerSocket();
                listenSocket.bind(serverAddr);
                textArea1.append("服务器开始等待客户端连接...\n");
            } catch (IOException e1) {
            }

            //创建线程
            new Thread(() -> {
                try {
                    while (true) {
                        toClientSocket = listenSocket.accept();
                        clientCounts++;
                        textArea1.append(toClientSocket.getRemoteSocketAddress() + "客户机编号：" + clientCounts + "会话线程开始...\n");
                        Thread clientThread = new ClientThread(toClientSocket, clientCounts,textArea1);
                        clientThread.start();
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
                }
            }).start();
        });


        EXIT_Button.addActionListener(e -> {
            try {
                if (listenSocket != null) {
                    listenSocket.close();
                }
                if (toClientSocket != null) {
                    toClientSocket.close();
                }
                System.exit(0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server_UI");
        frame.setContentPane(new Server_UI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        new Server_UI();
    }
}
