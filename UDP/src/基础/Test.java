package 基础;

import javax.swing.*;

public class Test {
    private JTextArea textArea1;
    private JPanel panel1;
    private JLabel IP_Port;
    private JTextField textField1;
    private JLabel Send_thing;
    private JTextField textField2;
    private JButton 发送消息;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setContentPane(new Test().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton 发送文件;
    private JButton 退出;
    private JTextArea textArea2;


}
