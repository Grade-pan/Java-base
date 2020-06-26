package chap05;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server_UI {
    private JPanel panel1;
    private JTextField txtHostName;
    private JTextField txtHostPort;
    private JButton btnStart;
    JTextArea txtArea;

    public Server_UI() {
        btnStart.addActionListener(e -> {
            try {
                //获取服务器工作地址端口
                String hostName = txtHostName.getText();
                int hostPort = Integer.parseInt(txtHostPort.getText());
                //创建UDP数据报套接字,在指定端口侦听
                DatagramSocket serverSocket = new DatagramSocket(hostPort);
                txtArea.append("服务器开始侦听...\n");
                //创建并启动UDP消息接收线程
                new Thread(new Server_ReceiveMessage(serverSocket, this)).start();
            } catch (NumberFormatException | SocketException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
            }
            btnStart.setEnabled(false);
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
