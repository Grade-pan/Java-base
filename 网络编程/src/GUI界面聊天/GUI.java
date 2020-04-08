package GUI界面聊天;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {


    public static void main(String[] args) {
        final String[] text = {" "};
        final String[] text1 = {""};

        JFrame jframe;
        JTextArea textArea;
        jframe = new JFrame();
        jframe.setTitle("孙大炮");
        jframe.setBounds(0, 0, 800, 800);

        JPanel jpanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();


        JButton jButton = new JButton("王浩哲点击这里发送");
        JButton jButton4 = new JButton("孙大炮点击这里发送");
        JButton jButton1 = new JButton("清除聊天记录");
        JButton jButton2 = new JButton("退出");
        JButton jButton3 = new JButton("王浩哲点击这里重新输入");
        JButton jButton5 = new JButton("孙大炮点击这里重新输入");

        JTextField jf1 = new JTextField(100);
        JLabel j1 = new JLabel("王浩哲输入框");
        JLabel j2 = new JLabel("孙大炮输入框");
        JTextField jf2 = new JTextField(65);
        textArea = new JTextArea(50, 50);

        //王浩哲发送按钮
        jButton.addActionListener(e -> {
            text[0] = methodA(jf1);
            System.out.println("王浩哲" + text[0]);
            methodB(text[0], textArea);
        });
        //孙大炮发送按钮
        jButton4.addActionListener(e -> {
            text1[0] = methodD(jf2);
            System.out.println("孙大炮" + text1[0]);
            methodB1(text1[0], textArea);
        });
        //清除聊天记录
        jButton1.addActionListener(e -> {
            //清除JTextArea的内容
            textArea.setText("");
        });
        //退出
        jButton2.addActionListener(e -> exit());
        //清空输入内容
        jButton3.addActionListener(e -> methodC(jf1));
        //清空输入内容
        jButton5.addActionListener(e -> methodC(jf2));

        jpanel.add(j1);
        jpanel.add(jf1);
        jpanel.add(j2);
        jpanel.add(jf2);
        jpanel.setLayout(new GridLayout(2, 1, 0, 0));

        jPanel2.add(jButton);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        jPanel2.add(jButton5);
        jPanel2.add(jButton1);
        jPanel2.add(jButton2);
        jPanel2.setLayout(new GridLayout(6, 1, 50, 100));

        jPanel1.add(textArea);

        jframe.add(jpanel, BorderLayout.NORTH);
        jframe.add(jPanel1, BorderLayout.CENTER);
        jframe.add(jPanel2, BorderLayout.WEST);
        jframe.setVisible(true);
    }


    private static String methodA(JTextField jf1) {
        String s = jf1.getText();
        return s;
    }

    private static String methodD(JTextField jf2) {
        String s = jf2.getText();
        return s;
    }

    private static void methodB(String str, JTextArea textArea) {
        textArea.append("王浩哲：" + str + "\r\n");
    }

    private static void methodB1(String str, JTextArea textArea) {
        textArea.append("孙大炮：" + str + "\r\n");
    }

    private static void methodC(JTextField jf1) {
        jf1.setText("");
    }

    private static void exit() {
        System.exit(0);
    }
}
