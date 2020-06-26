package TEST;

import 实验测试.WAY_UI;

import javax.swing.*;

public class test1 implements WAY_UI {
    public static void main(String[] args) {
        String[] text = {""};
        JFrame f = new JFrame();
        JPanel jp = new JPanel();
        JButton jb = new JButton("发送");
        JTextField jt = new JTextField(20);

        jp.add(jb);
        jp.add(jt);
        f.add(jp);
        f.setTitle("123");
        f.setBounds(0,0,500,500);
        f.setVisible(true);


        jb.addActionListener(e -> {
            text[0] = WAY_UI.getText(jt);
            System.out.println(text[0]);
        });
    }
}
