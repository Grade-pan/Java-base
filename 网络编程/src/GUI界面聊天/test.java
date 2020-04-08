package GUI界面聊天;

import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JButton jb1=new JButton("111");
        JButton jb2=new JButton("111");
        JButton jb3=new JButton("111");
        JButton jb4=new JButton("111");
        JButton jb5=new JButton("111");
        JButton jb6=new JButton("111");

        JLabel a=new JLabel("dapao");
        JLabel b=new JLabel("dapao");

        jp1.add(a);
        jp1.add(jb1);
        jp1.add(b);
        jp1.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);
        jp2.add(jb5);


        jf.add(jp1,BorderLayout.NORTH);
        jf.add(jp2,BorderLayout.SOUTH);
        jf.add(jb6,BorderLayout.CENTER);


        jf.setBounds(0, 0, 500, 500);
        jf.setVisible(true);
    }
}
