package chap04;

import Chap2.ClientThread;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client_Input extends Thread {
    private Selector selector;
    private SocketChannel socketChannel;
    private JButton send_msg_Button;
    private JButton send_field_Button;
    private JTextField Msg_field;


    public Client_Input(Selector selector, SocketChannel socketChannel, JButton send_msg_Button, JButton send_field_Button, JTextField Msg_field) {
        this.selector = selector;
        this.socketChannel = socketChannel;
        this.send_msg_Button = send_msg_Button;
        this.Msg_field = Msg_field;
        this.send_field_Button = send_field_Button;

    }

    @Override
    public void run() {
        send_msg_Button.addActionListener(e -> {
            try {
                String msg = Msg_field.getText();
                if (msg.equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入发送内容", "提示", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024*40);
                byteBuffer.putInt(-1);
                byte [] bytes=msg.getBytes();
                byteBuffer.putInt(bytes.length);
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socketChannel.register(selector, SelectionKey.OP_WRITE,byteBuffer);
                selector.wakeup();
                Msg_field.setText("");
            } catch (ClosedChannelException ex) {
                ex.printStackTrace();
            }
        });

        send_field_Button.addActionListener(e -> {
            JLabel label = new JLabel("请选择文件");
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.showDialog(label, "选择");
            File file = chooser.getSelectedFile();
            System.out.println(file);


            try {
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024*40);
                byteBuffer.putInt(1);//文件状态
                String Filename= file.getName();//文件名
                byte [] bytes=Filename.getBytes("UTF-8");//转换成字节
                byteBuffer.putInt(bytes.length);//文件名长度
                byteBuffer.put(bytes);//文件名
                byteBuffer.putLong(file.length());//文件大小
                byteBuffer.flip();;
                socketChannel.register(selector, SelectionKey.OP_WRITE, byteBuffer);
                selector.wakeup();
                new Thread(new send_File(socketChannel, selector, file)).start();//启动线程开始发送文件
            } catch (ClosedChannelException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        });
    }
}
