package Chap2;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client_UI {
    private JPanel panel1;
    private JTextField textField1;
    private Socket clientSocket = null;
    private BufferedReader in;
    private PrintWriter out;
    private JTextField textField2;
    private JButton Connect_Button;
    private JTextField textField3;
    private JButton Send_Button;
    private JTextArea textArea1;
    private JButton EXIT_Button;


    public Client_UI() {
        Connect_Button.addActionListener(e -> {
            try {
                String remoteName = textField1.getText();
                int remotePort = Integer.parseInt(textField2.getText());
                SocketAddress remoteAddr = new InetSocketAddress(InetAddress.getByName(remoteName), remotePort);

                clientSocket = new Socket();
                clientSocket.connect(remoteAddr);
                textArea1.append("连接到服务器成功，会话开始...\n");

                out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "连接错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        });
        Send_Button.addActionListener(e -> {
            if (clientSocket == null) {
                JOptionPane.showMessageDialog(null, "请先检查服务器连接情况。确保客户机连接到服务器！", "错误提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String outStr = textField3.getText();
            if (outStr.length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入发送消息！", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            out.println(outStr);
            textField3.setText("");
            try {
                String inStr;
                inStr = in.readLine();
                textArea1.append("服务端：" + inStr + "\n");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "客户机接收消息错误！", "错误提示", JOptionPane.ERROR_MESSAGE);
            }
        });
        EXIT_Button.addActionListener(e -> {
            try {
                if (in != null)
                    in.close();
                if (out != null) {
                    out.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
                System.exit(0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Client_UI");
        frame.setContentPane(new Client_UI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Client_UI client_ui = new Client_UI();
    }
}
