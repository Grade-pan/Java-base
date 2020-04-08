package NIO和TCP;

import javax.swing.*;

public class Server_UI {
    static final String path="E:\\aaaaaa\\wenjian.txt";
    static final int BUFFER_SIZE = 5*1024*1024;
    private static JFrame jFrame = new JFrame();
    private static JPanel jPanel = new JPanel();
    private static JButton jb = new JButton("EXIT");
    private static JButton jb1 = new JButton("START");
//    static JButton jb3 = new JButton("RECEIVE_FILE");
//    static JButton jb4=new JButton("RECEIVE_MESSAGE");
    static JButton jb2 = new JButton("SEND");
    static JTextField jt = new JTextField(20);
    static JTextArea jte = new JTextArea(40, 40);

    public static void main(String[] args) {
        //UI界面
        jFrame.setTitle("NIO——SERVER");
        jPanel.add(jb1);
        jPanel.add(jt);
        jPanel.add(jb2);
//        jPanel.add(jb3);
//        jPanel.add(jb4);
        jPanel.add(jte);
        jPanel.add(jb);
        jFrame.add(jPanel);
        jFrame.setBounds(0, 0, 500, 755);
        jFrame.setVisible(true);


        //退出按钮
        jb.addActionListener(e -> {
            System.exit(0);
        });
        //开始按钮
        jb1.addActionListener(e -> {
            Server server = new Server(8080);
            new Thread(server).start();
        });
    }
}
