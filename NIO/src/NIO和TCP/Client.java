package NIO和TCP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.FutureTask;

class Client extends Thread {
    private String IP;
    private int port;

    public Client(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }


    public void run() {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(IP, port));
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (true) {
                if (socketChannel.isConnectionPending()) {
                    Client_UI.jte.append(df.format(new Date()) + "  正在连接服务器中......" + "\r\n");
                    System.out.println("正在连接服务器中......");
                }
                // 设置300s的超时时间，进行IO事件选择操作
                int nSelectedKeys = selector.select(300000);//selector轮询监听
                if (nSelectedKeys > 0) {//监听到事件
                    for (SelectionKey s_key : selector.selectedKeys()) {
                        //判断检测到的channel是不是可连接的，将对应的channel注册到选择器上，指定关心的事件类型为OP_READ
                        if (s_key.isConnectable()) {
                            SocketChannel connChannel = (SocketChannel) s_key.channel();
                            connChannel.configureBlocking(false);
                            connChannel.register(selector, SelectionKey.OP_READ);
                            connChannel.finishConnect();

                            //启动一个写线程
                            Client_Write write_thread = new Client_Write(connChannel);
                            FutureTask<String> task = new FutureTask<String>(write_thread);
                            new Thread(task).start();

                            System.out.println(df.format(new Date()) + "  连接服务器成功......");
                            Client_UI.jte.append(df.format(new Date()) + "  连接服务器成功......" + "\r\n");
                        }
                        //若检测到的IO事件是读事件，则处理相关数据的读相关的业务逻辑
                        else if (s_key.isReadable()) {
                            SocketChannel readChannel = (SocketChannel) s_key.channel();
                            StringBuilder sb = new StringBuilder();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(Server_UI.BUFFER_SIZE);

                            while (readChannel.read(byteBuffer) > 0) {
                                byteBuffer.flip();
                                sb.append(StandardCharsets.UTF_8.decode(byteBuffer).toString());
                                System.out.println(df.format(new Date()) + "  服务端:  " + sb);
                                Client_UI.jte.append(df.format(new Date()) + "  服务端:  " + sb + "\r\n");
                                byteBuffer.clear();
                            }
                        }
                    }
                    // 一次监听的事件处理完毕后，需要将已经记录的事件清除掉，准备下一轮的事件标记
                    selector.selectedKeys().clear();
                } else {
                    System.err.println(df.format(new Date()) + "  客户端30s无操作，自动断开连接");
                    Client_UI.jte.append(df.format(new Date()) + "  客户端30s无操作，自动断开连接" + "\r\n");
                    socketChannel.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
