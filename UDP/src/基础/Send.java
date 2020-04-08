package 基础;

import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class Send extends Thread {
    private static JFrame f = new JFrame("Send");
    private static JPanel jPanel = new JPanel();
    private static JButton Send_Button = new JButton("发送");
    private static JButton Exit_Button = new JButton("退出");
    private static JTextField jTextField = new JTextField(20);
    private static JTextArea jTextArea = new JTextArea(40, 40);
    private static DatagramSocket datagramSocket = null;
    private static DatagramPacket datagramPacket = null;
    //...................................................................................................
    private String Flag;
    private String IP;

    public Send(String Flag, String IP) {
        this.Flag = Flag;
        this.IP = IP;
    }

    public void run() {
//.................................UI..................................................................
        jPanel.add(jTextField);
        jPanel.add(Send_Button);
        jPanel.add(jTextArea);
        jPanel.add(Exit_Button);
        f.add(jPanel);
        f.setBounds(0, 0, 500, 800);
        f.setVisible(true);
//......................................................................................................


        try {
            datagramSocket = new DatagramSocket();
            System.out.println("等待发送数据中......");
            jTextArea.append("等待发送数据中......" + "\r\n");
            Send_Button.addActionListener(e -> {
                String str = jTextField.getText();
                if (str.equals("")) {
                    System.err.println("输出为空......");
                }
                jTextArea.append(str + "\r\n");
                Flag = str;
                try {
                    datagramPacket = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName(IP), 8888);
                    datagramSocket.send(datagramPacket);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                jTextField.setText("");
//............................................................................................................................
                Exit_Button.addActionListener(e1 -> {
                    jTextArea.append(datagramSocket.getPort() + "连接已断开......" + "\r\n");
                    datagramSocket.close();
                    System.exit(0);
                });
            });

        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println(datagramSocket.getPort() + "连接已断开......");


    }
}
