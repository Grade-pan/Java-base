package chap04;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIO_Client {
    private static ByteBuffer recvBuff = ByteBuffer.allocate(1024 * 1024 * 40);//接收缓冲区
    private static ByteBuffer sendBuff = ByteBuffer.allocate(1024 * 1024 * 40);//发送缓冲区
    private static SocketChannel clientChanel = null;//会话通道
    private static Charset charset = StandardCharsets.UTF_8;
    private static Selector selector = null;
    private static String txtHost = null;//主机
    private static int txtPort;//端口

    static void init(JTextField Host_field, JTextField Port_field, JTextArea Txt_area, JButton send_msg_button, JTextField Msg_field, JButton send_filed_Button) {
        try {
            txtHost = Host_field.getText();//主机名
            txtPort = Integer.parseInt(Port_field.getText());//端口
            SocketAddress remoteAddr = new InetSocketAddress(InetAddress.getByName(txtHost), txtPort);
            clientChanel = SocketChannel.open();//创建客户机会话通道
            clientChanel.configureBlocking(false);
            clientChanel.connect(remoteAddr);//连接远程服务器
            selector = Selector.open();
            new Client_Input(selector, clientChanel, send_msg_button, send_filed_Button, Msg_field).start();//监控输入线程
            clientChanel.register(selector, SelectionKey.OP_CONNECT);
            Monitor_round(Txt_area, send_msg_button, Msg_field);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void Monitor_round(JTextArea Txt_area, JButton send_msg_button, JTextField Msg_field) {
        new Thread(() -> {
            while (true) {
                if (clientChanel.isConnectionPending()) {
                    Txt_area.append("服务器正在连接中...\n");
                }
                try {
                    int nKeys = selector.select();
                    if (nKeys == 0)
                        continue;
                    Set<SelectionKey> readyKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = readyKeys.iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        if (key.isValid() && key.isConnectable()) {
                            doConnect(key, Txt_area);
                        }
                        if (key.isValid() && key.isReadable()) {
                            doRead(key, Txt_area);
                        }
                        if (key.isValid() && key.isWritable()) {
                            doWrite(key, Txt_area);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void doConnect(SelectionKey key, JTextArea Txt_area) throws IOException {
        SocketChannel connChannel = (SocketChannel) key.channel();
        connChannel.configureBlocking(false);
        connChannel.register(selector, SelectionKey.OP_READ);
        connChannel.finishConnect();
        Txt_area.append("已连接上服务器...\n");
    }

    private static void doRead(SelectionKey key, JTextArea Txt_area) throws IOException {
        SocketChannel readChanel = (SocketChannel) key.channel();
        StringBuilder sb = new StringBuilder();
        while (readChanel.read(recvBuff) > 0) {
            recvBuff.flip();
            sb.append(charset.decode(recvBuff).toString());
            Txt_area.append(sb + "\n");
            recvBuff.clear();
        }
    }

    private static void doWrite(SelectionKey key, JTextArea Txt_area) throws IOException {
        SocketChannel writeChanel = (SocketChannel) key.channel();
        writeChanel.write((ByteBuffer)key.attachment());
        writeChanel.register(selector, SelectionKey.OP_READ);
       // key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
    }
}
