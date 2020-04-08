package chap04;

import javax.swing.*;

public class NIO_Client_UI {
    private JTextField Host_field;
    private JTextField Port_field;
    private JButton connect_Button;
    private JTextArea textArea1;
    private JButton send_file_Button;
    private JButton send_msg_Button;
    private JPanel jpanel;
    private JLabel Host_name;
    private JLabel Portname;
    private JTextField Msg_field;

    public NIO_Client_UI() {
        connect_Button.addActionListener(e -> {
            connect_Button.setEnabled(false);
            NIO_Client.init(Host_field, Port_field, textArea1, send_msg_Button, Msg_field, send_file_Button);
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NIO_Client_UI");
        frame.setContentPane(new NIO_Client_UI().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        new NIO_Client_UI();
    }


}
