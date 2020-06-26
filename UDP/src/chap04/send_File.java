package chap04;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class send_File extends Thread {
    private SocketChannel socketChannel;
    private Selector selector;
    private File file;

    public send_File(SocketChannel socketChannel, Selector selector, File file) {
        this.file = file;
        this.selector = selector;
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            FileInputStream fin = new FileInputStream(file);
            FileChannel channel = fin.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 40);
            byteBuffer.putInt(2);//读文件
            while (channel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
//                    socketChannel.register(selector, SelectionKey.OP_WRITE);
//                    selector.wakeup();
                byteBuffer.clear();
            }
            socketChannel.register(selector, SelectionKey.OP_READ);
//            if (num == -1) {
//               socketChannel.shutdownOutput();
//          }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
