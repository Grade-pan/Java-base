package NIO和TCP;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class Client_UI {
    static String Flag = "???????";//设置一个退出标志
    private static JFrame jFrame = new JFrame();
    private static JPanel jPanel = new JPanel();
    private static JButton jb = new JButton("EXIT");
    private static JButton jb1 = new JButton("CONNECT");
    static JButton jb3=new JButton("SEND");
    static JButton jb4=new JButton("SEND_File");
    static JTextField jt = new JTextField(20);
    static JTextArea jte = new JTextArea(40, 40);

    public static void main(String[] args) throws IOException {

        //UI界面
        jFrame.setTitle("NIO——Client");
        jPanel.add(jb1);
        jPanel.add(jt);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jte);
        jPanel.add(jb);
        jFrame.add(jPanel);
        jFrame.setBounds(0, 0, 500, 755);
        jFrame.setVisible(true);


        //退出按钮
        jb.addActionListener(e -> {
            Flag = "quit";
            System.exit(0);
        });
        //启动按钮
        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String IP = "127.0.0.1";
                int Port = 8080;
                Client client = new Client(IP, Port);
                new Thread(client).start();
            }
        });
    }
}
