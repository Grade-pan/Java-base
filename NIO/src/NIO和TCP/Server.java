package NIO和TCP;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class Server extends Thread {


    private int Port;

    public Server(int Port) {
        this.Port = Port;
    }

    public void run() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(Port));
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            Server_UI.jte.append("正在等待客户端连接" + "\r\n");
            while (true) {
                if (selector.select() == 0) {
                    System.out.println("**********");
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        //启动线程用来写
                        Server_Write server_write = new Server_Write(socketChannel);
                        new Thread(server_write).start();

                        System.out.println(df.format(new Date()) + ":  端口号为" + socketChannel.socket().getPort() + "的客户端连接成功");
                        Server_UI.jte.append(df.format(new Date()) + ":  端口号为" + socketChannel.socket().getPort() + "的客户端连接成功" + "\r\n");
                    }
                    if (key.isReadable()) {
                        SocketChannel readChannel = (SocketChannel) key.channel();
                        StringBuilder sb = new StringBuilder();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(Server_UI.BUFFER_SIZE);

                        while (readChannel.read(byteBuffer) > 0) {
                            byteBuffer.flip();
                            sb.append(StandardCharsets.UTF_8.decode(byteBuffer).toString());
                            System.out.println(df.format(new Date()) + ":  端口号为" + readChannel.socket().getPort() + "的客户端:  " + sb);
                            Server_UI.jte.append(df.format(new Date()) + ":  端口号为" + readChannel.socket().getPort() + "的客户端:  " + sb + "\r\n");
                            byteBuffer.clear();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
