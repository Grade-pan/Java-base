package NIO和TCP;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class Client_Write implements Callable<String>, GUI_way {
    private SocketChannel socketChannel;

    public Client_Write(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public String call() {

        while (!Client_UI.Flag.equals("quit")) {
            Client_UI.jb3.addActionListener(e -> {
                String msg = GUI_way.getText(Client_UI.jt);
                GUI_way.Clear_Text(Client_UI.jt);
                if (msg.equals("")) {
                    System.out.println();
                }
                try {
                    socketChannel.write(StandardCharsets.UTF_8.encode(msg));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            Client_UI.jb4.addActionListener(e -> {
                JLabel label = new JLabel("请选择文件");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showDialog(label, "选择");
                File file = chooser.getSelectedFile();

                Charset charset = Charset.forName("GBK");
                CharsetDecoder charDecoder = charset.newDecoder();
                CharBuffer charBuffer = CharBuffer.allocate(5 * 1024 * 1024);
                try {
                    FileInputStream fin = new FileInputStream(file);
                    FileChannel channel = fin.getChannel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(5 * 1024 * 1024);
                    while (channel.read(byteBuffer) != -1) {
                        byteBuffer.flip();
                        charDecoder.decode(byteBuffer, charBuffer, true);
                        charBuffer.flip();
                        System.out.println("start......");
                        System.out.println(charBuffer.toString());
                        socketChannel.write(StandardCharsets.UTF_8.encode(charBuffer));

                        int sub = byteBuffer.limit() - byteBuffer.position();
                        byteBuffer.clear();
                        charBuffer.clear();
                        channel.position(channel.position() - sub);
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            });
        }

        System.out.println("端口号为" + socketChannel.socket().getPort() + "的客户端已退出,不再发送消息");
        return "BYE";
    }
}
