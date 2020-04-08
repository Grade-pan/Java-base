package NIO和TCP;

import javax.swing.*;
import java.io.*;

public class Test1 {
    public static void main(String[] args) {
        JFrame f = new JFrame("123");
        JPanel jp = new JPanel();

        JButton Send = new JButton("发送");
        JTextField jTextField = new JTextField(20);
        JLabel label = new JLabel("请选择文件");

        jp.add(jTextField);
        jp.add(Send);

        f.add(jp);
        f.setBounds(0, 0, 500, 500);
        f.setVisible(true);

        Send.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.showDialog(label, "选择");
            File file = chooser.getSelectedFile();
            System.out.println(file.getAbsoluteFile().toString());

            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line = in.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = in.readLine();
                }
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
