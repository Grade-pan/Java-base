package chap04;

import javax.swing.*;

public class NIO_Server_UI {
    private JPanel panel1;
    private JTextField Host_field;
    private JTextField Port_field;
    private JButton start_Button;
    private JTextArea Txt_area;


    public NIO_Server_UI() {
        start_Button.addActionListener(e -> {
            NIO_Server.init(Host_field, Port_field, Txt_area);
            Txt_area.append("服务器开始侦听客户端连接...\n");
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NIO_Server_UI");
        frame.setContentPane(new NIO_Server_UI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        new NIO_Server_UI();
    }


}
