package 实验测试;

import javax.swing.*;

public class Client_UI {
    JPanel jp = new JPanel();
    JButton jb = new JButton("确认发送");
    JButton jb1 = new JButton("退出");
    JButton jb3 = new JButton("确认");
    JTextField jt = new JTextField(11);
    JTextField jt1 = new JTextField(11);

    String[] arr = {"I", "am", "sun", "da", "pao"};
    JLabel jl = new JLabel("选择客户端");
    JLabel jl1 = new JLabel("消息输入");
    JLabel jl2 = new JLabel("选择的客户端的IP和端口号是");
    JLabel jl3 = new JLabel("发送和接收的的消息在下边显示");
    JTextArea jte = new JTextArea(25, 40);
    JComboBox jc = new JComboBox(arr);
}