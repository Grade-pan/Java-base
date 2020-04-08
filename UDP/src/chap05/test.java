package chap05;

import javax.swing.*;

public class test {
    private static JFrame f = new JFrame("Client_UI");
    private static JPanel panel1 = new JPanel();
    static JTextArea txtArea = new JTextArea();
    private static JTextArea txtInput = new JTextArea();
    private static JButton btnsend = new JButton("发送");
    private static JButton btnexit=new JButton("退出");
    private static JLabel label = new JLabel("会话消息窗口");
    private static JLabel label1 = new JLabel("发言窗口");
    private static JLabel label2 = new JLabel("在线用户");
    static JList userlist;
    static JScrollPane jScrollPane;

    public test() {
        panel1.setLayout(null);
        label.setBounds(0, 0, 100, 25);
        txtArea.setBounds(0, 30, 200, 200);
        label1.setBounds(0, 240, 100, 25);
        txtInput.setBounds(0, 290, 200, 200);
        btnsend.setBounds(100, 500, 80, 25);
        btnexit.setBounds(400,500,80,25);
        label2.setBounds(220, 0, 100, 25);


        panel1.add(label);
        panel1.add(txtArea);
        panel1.add(label1);
        panel1.add(txtInput);
        panel1.add(btnsend);
        panel1.add(btnexit);
        panel1.add(label2);
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listModel.add(0, "QQ小冰");
        listModel.add(1, "中国知名作家刘信达");
        listModel.add(2, "黑猫警长giao哥skrGiaoSkrGiao我们一起叫");
        listModel.add(3, "中华**创始人 **");
        userlist = new JList(listModel);
        jScrollPane=new JScrollPane(userlist);
        jScrollPane.setBounds(220, 40, 260, 450);
        panel1.add(jScrollPane);
        f.add(panel1);
        f.setVisible(true);
        f.setBounds(0, 0, 510, 580);
        f.setDefaultCloseOperation(3);

    }

    public static void main(String[] args) {
        new test();
    }
}
