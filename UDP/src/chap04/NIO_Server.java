package chap04;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIO_Server {
    private static ServerSocketChannel listenChanel = null;
    private static Selector selector;
    private static String txtHost = null;//主机
    private static int txtPort;//端口
    private static String filename = null;//文件名

    static void init(JTextField Host_field, JTextField Port_field, JTextArea Txt_area) {
        try {
            txtHost = Host_field.getText();
            txtPort = Integer.parseInt(Port_field.getText());
            SocketAddress serveraddr = new InetSocketAddress(InetAddress.getByName(txtHost), txtPort);
            selector = Selector.open();
            listenChanel = ServerSocketChannel.open();
            listenChanel.socket().bind(serveraddr);
            listenChanel.configureBlocking(false);
            listenChanel.register(selector, SelectionKey.OP_ACCEPT);
            Monitor_round(Txt_area);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void Monitor_round(JTextArea Txt_area) {
        new Thread(() -> {
            try {
                while (true) {
                    int nKeys = selector.select();
                    if (nKeys == 0)
                        continue;
                    Set<SelectionKey> readyKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = readyKeys.iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        if (key.isAcceptable()) {
                            doAccept(key, Txt_area);
                        }
                        if (key.isReadable()) {
                            doRead(key, Txt_area);
                        }
                    }
                    //结束服务端
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void doAccept(SelectionKey key, JTextArea Txt_area) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel clientChanel = serverSocketChannel.accept();
            Txt_area.append("服务器建立了与客户机的会话通道：" + clientChanel + "\n");
            clientChanel.configureBlocking(false);
            clientChanel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doRead(SelectionKey key, JTextArea Txt_area) throws IOException {
        ByteBuffer recvBuff = ByteBuffer.allocate(1024 * 1024 * 40);//接收缓冲区
        ByteBuffer sendBuff;//发送缓冲区
        Charset charset = StandardCharsets.UTF_8;
        SocketChannel clientChanel = (SocketChannel) key.channel();
        StringBuilder sb = new StringBuilder();
        while (clientChanel.read(recvBuff) > 0) {
            recvBuff.flip();//缓冲区指针回到数据起点
            int flag = recvBuff.getInt(); //判断接收类型-1，文本数据，1，文件信息，2，文件数据
            if (flag == -1) {
                Txt_area.append("接收的是文本数据......" + "\r\n");
                while (recvBuff.hasRemaining()) {
                    int length = recvBuff.getInt();
                    byte[] name_bytes = new byte[length];
                    recvBuff.get(name_bytes);
                    String msg = new String(name_bytes, 0, length, "utf-8");
//                    sb.append(charset.decode(recvBuff).toString());
                    Txt_area.append("客户端IP和端口：" + clientChanel.getRemoteAddress() + "  消息:  " + msg + "\n");
                }
            }
            if (flag == 1) {
                Txt_area.append("接收的是文件数据......" + "\r\n");
                while (recvBuff.hasRemaining()) {
                    int filename_length = recvBuff.getInt();//文件名长度
                    byte[] name_bytes = new byte[filename_length];
                    recvBuff.get(name_bytes);
                    filename = new String(name_bytes, 0, filename_length, "UTF-8");//文件名
                    Long file_size = recvBuff.getLong();
                    Txt_area.append("客户端IP和端口：" + clientChanel.getRemoteAddress() + "  消息:  " + "文件名称：" + filename + "文件大小" + file_size + "B" + "\n");
                }
            }
            if (flag == 2) {
                FileOutputStream fos = new FileOutputStream("D:\\untitled\\UDP\\file\\"+filename);
                FileChannel channel = fos.getChannel();
                Txt_area.append("开始接收文件......" + "\r\n");
                while (recvBuff.hasRemaining()) {
                    channel.write(recvBuff);
                }
                Txt_area.append("文件接收完成......" + "\r\n");
            }
            recvBuff.clear();//清空接收缓冲区
        }
        //发送
        sendBuff = ByteBuffer.wrap("消息已收到".getBytes(charset));
        clientChanel.write(sendBuff);
    }
}
