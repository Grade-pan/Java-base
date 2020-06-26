package NIO和TCP;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class Test_READ {
    public static void main(String[] args) {
        Charset charset = Charset.forName("GBK");
        CharsetDecoder charDecoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024 * 1024 * 20);
        File file = new File("E:\\Java网络编程\\课件密码说明.doc");
        File file1 = new File("C:\\Users\\18178\\Desktop\\2222222.txt");
        long startTime = System.nanoTime(); //获取开始时间
        try {
            FileInputStream fin = new FileInputStream(file);
            FileChannel channel = fin.getChannel();
//            StringBuilder sb=new StringBuilder();

//            FileOutputStream fout = new FileOutputStream(file1);
//            FileChannel fc = fout.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 20);
            byteBuffer.putInt(255);
            byteBuffer.putLong(222222222);
            //String msg = "中华";
            System.out.println(file.getName());
            byte[] bytes = file.getName().getBytes();
            byteBuffer.putInt(bytes.length);
            byteBuffer.put(bytes);

            byteBuffer.flip();
//            System.out.println(byteBuffer.getInt());
//            System.out.println(byteBuffer.getLong());
//            int length = byteBuffer.getInt();
//
//
//            byte[] name_bytes = new byte[length];
//            byteBuffer.get(name_bytes);
//            String filename = new String(name_bytes, 0, length, "utf-8");
//            System.out.println(filename);

//            charDecoder.decode(byteBuffer.get(name_bytes), charBuffer, true);
//            charBuffer.flip();
//            System.out.println(charBuffer);

            while (channel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                System.out.println(byteBuffer.getInt());
                System.out.println(byteBuffer.getLong());
                int length = byteBuffer.getInt();


                byte[] name_bytes = new byte[length];
                byteBuffer.get(name_bytes);
                String filename = new String(name_bytes, 0, length, "utf-8");
                System.out.println(filename);
//                byteBuffer.position(1);
//                charDecoder.decode(byteBuffer, charBuffer, true);
//                charBuffer.flip();
//                System.out.println("start......");
//                System.out.println(charBuffer.toString());
//                byteBuffer.flip();
//                System.out.println(byteBuffer.getInt());
//                System.out.println(byteBuffer.getLong());
//                int length=byteBuffer.getInt();
//                byte namebytes[] = new byte[4];
//                byteBuffer.get(namebytes);
//                String filename = new String(namebytes, 0, 4, "UTF-8");
//                System.out.println(filename);
//                while (byteBuffer.hasRemaining()) {
//                    fc.write(byteBuffer);
//                }
//                int sub = byteBuffer.limit() - byteBuffer.position();
//                byteBuffer.clear();
//                charBuffer.clear();
//                channel.position(channel.position() - sub);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();//获取结束时间
        System.out.println("完成500mb，缓冲区大小为20mb，的读写所需要的时间为   " + (endTime - startTime) + "ns");
    }
}
